package com.kumar.crackup.util

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.toObject
import com.kumar.crackup.UserModel
import com.kumar.crackup.model.Exam
import com.kumar.crackup.model.FireUser
import com.kumar.crackup.model.NewQuestion
import com.kumar.crackup.model.QueryModel
import com.kumar.crackup.model.Question
import com.kumar.crackup.model.SubTopic
import com.kumar.crackup.model.TamilUnit
import com.kumar.crackup.model.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

object FirebaseUtil {

    fun getFireAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    suspend fun signUpUser(input: CreateAccountFormInput): AuthResult {
        val fireAuth = getFireAuth()
        val result = fireAuth.createUserWithEmailAndPassword(input.userEmail, input.password).await()
        Log.d("SINGNUPC", "signUpUser: ${result}, ${result.credential}, ${result.describeContents()}")
        return result
    }

    suspend fun loginUser(
        userName: String,
        password: String,
    ) : AuthResult {
        val firebaseAuth = getFireAuth()

        return firebaseAuth.signInWithEmailAndPassword(userName, password).await()

    }

    suspend fun addUser(authResult: AuthResult, input: CreateAccountFormInput, onSuccess: () -> Unit, onError: () -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val deviceName = getDeviceName()
        try {
            firestore.collection("users")
                .document(authResult.user?.uid.toString())
                .set(
                    mapOf(
                        "name" to input.fullName,
                        "email" to input.userEmail,
                        "mobile" to input.mobileNumber,
                        "district" to input.district,
                        "exam" to input.exam,
                        "deviceName" to deviceName,
                        "isSubscribed" to false,
                        "pyq" to false,
                        "generalTamil" to false,
                        "generalStudies" to false,
                        "aptitude" to false,
                        "fullPackage" to false,
                        "createdAt" to  FieldValue.serverTimestamp(),
                        "lastLogin" to "",
                        "activeSession" to "",
                    )
                ).await()
            onSuccess()
        } catch (e: Exception) {
            onError()
        }
    }

//    fun updateUser(navController: NavController, avatarModel: AvatarModel, userName: String) {
//        val firestore = FirebaseFirestore.getInstance()
//        val fAuth = getFireAuth()
//        firestore.collection("users")
//            .document(fAuth.uid.toString())
//            .set(
//                mapOf(
//                    "uid" to fAuth.uid.toString(),
//                    "avatarChoice" to avatarModel.imgIndex,
//                    "fcm_token" to task.result,
//                    "userName" to userName
//                ), SetOptions.merge()
//            ).addOnCompleteListener {
//                navController.navigate("home") {
//                    popUpTo(0)
//                }
//            }
//    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    fun getAuthFlow(): Flow<FirebaseUser?> = callbackFlow {
        val auth = FirebaseAuth.getInstance()
        val listener = FirebaseAuth.AuthStateListener{firebaseAuth ->
            trySend(firebaseAuth.currentUser)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }
    fun getCurrentUser(): Flow<UserModel?> = getAuthFlow().flatMapLatest { firebaseUser ->
        if(firebaseUser == null) {
            flowOf(null)
        } else {
            callbackFlow {
                Log.d("FIREf", "getCurrentUser: first")
                val uid = getFireAuth().currentUser?.uid

                Log.d("FIREf", "getCurrentUser: ${uid}")
                if (uid == null) {
                    trySend(null)
                    close()
                    return@callbackFlow
                }

                val listener = FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(uid)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }

                        val user =
                            value?. let { item ->
                                UserModel(
                                    name = item.get("name").toString(),
                                    email = item.get("email").toString(),
                                    mobile = item.get("mobile").toString(),
                                    district = item.get("district").toString(),
                                    exam = item.get("exam").toString(),
                                    isSubscribed = item.getBoolean("isSubscribed"),
                                    pyq = item.getBoolean("pyq"),
                                    generalTamil = item.getBoolean("generalTamil"),
                                    generalStudies = item.getBoolean("generalStudies"),
                                    aptitude = item.getBoolean("aptitude"),
                                    fullPackage = item.getBoolean("fullPackage"),
                                )
                            }
                        trySend(user)
                    }
                awaitClose { listener.remove() }
            }
        }

    }

    fun putTopics(topicModel: Topic) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("topics").document().set(topicModel)
    }
    fun putSubTopics(topicModel: SubTopic) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("subTopics").document().set(topicModel)
    }
    fun putExams(examModel: Exam) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("exams").document().set(examModel)
    }
    fun putNewQuestion(questionModel: NewQuestion) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("new-questions").document(questionModel.mainTopic + "_" + questionModel.subtopic + "_" + questionModel.subCollection + "_" + questionModel.qno).set(questionModel)
    }
    fun putTamil(examModel: TamilUnit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("tamil-units").document().set(examModel)
    }
    suspend fun getTopics() : List<Topic> {
        val firestore = FirebaseFirestore.getInstance()
        return firestore.collection("topics")
            .orderBy("order")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(Topic::class.java)?.copy(id = it.id) }
    }

    suspend fun getExams(): List<Exam> {
        val firestore = FirebaseFirestore.getInstance()
        try {
            return firestore.collection("exams")
                .orderBy("order")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Exam::class.java)?.copy(id = it.id) }

        } catch (e: Exception) {
        }
        return emptyList()
    }
    suspend fun getTamil(): List<TamilUnit> {
        val firestore = FirebaseFirestore.getInstance()
        try {
            val result = firestore.collection("tamil-units")
                .orderBy("order")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(TamilUnit::class.java)?.copy(id = it.id) }
            Log.d("LUCKY", "getTamil: ${result}")
                return result
        } catch (e: Exception) {
            Log.d("LUCKY", "getTamil error : ${e.message}")

        }
        return emptyList()
    }
    suspend fun getSubTopics(topicId: String): List<SubTopic> {
        val firestore = FirebaseFirestore.getInstance()
        Log.d("LUCKY", "getSubTopics: ${topicId}")
        try {
            val result = firestore.collection("subTopics")
                .orderBy("order")
                .whereEqualTo("topicId", topicId)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(SubTopic::class.java) }
            Log.d("LUCKY", "getSubTopics: ${result}")
                return result
        } catch (e: Exception) {
            Log.d("LUCKY", "getSubTopics error : ${e.message}")
        }
        return emptyList()
    }

    suspend fun getQuestions(query: QueryModel): List<NewQuestion> {
        val firestore = FirebaseFirestore.getInstance()
//        Log.d("LUCKY", "getSubTopics: ${topicId}")
        try {
            val result = firestore.collection("new-questions")
                .whereEqualTo("mainTopic", query.mainTopic)
                .whereEqualTo("subtopic", query.subTopic)
                .whereEqualTo("subCollection", query.subCollection)
                .orderBy("qno")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(NewQuestion::class.java) }
            Log.d("LUCKY", "getSubTopics: ${result.size}, ${result}")
                return result
        } catch (e: Exception) {
            Log.d("LUCKY", "getSubTopics error : ${e.message}")
        }
        return emptyList()
    }

    suspend fun exportQuestionsToLocalFile(
        context: Context,
        firestore: FirebaseFirestore,
        collectionName: String = "questions",
        outputFileName: String = "new_${collectionName}_export.json"
    ): File = withContext(Dispatchers.IO) {
        val snapshot = firestore.collection(collectionName).get().await()

//        val questions: List<Question> = snapshot.documents.mapNotNull { doc ->
//            // toObject fills in whatever matches; .copy(id = doc.id) makes sure
//            // the REAL Firestore ID wins over any stray "id" field inside the
//            // document's own data (same fix as before — avoids the mismatch
//            // where a manually-set id field doesn't match the actual doc path).
//            doc.toObject(Question::class.java)?.copy(id = doc.id)
//        }

        val questions = mutableListOf<NewQuestion>()
            snapshot.documents.forEach {
            try {
//                questions.add(mapDocumentSafely(it))
                questions.add(mapDocumentSafelyNew(it))
            } catch (e: Exception) {
                Log.d("FIRECRASH", "exportQuestionsToLocalFile: ${it.get("q_en")} ${e.message}")
                return@forEach
            }
        }

        val json = Json {
            prettyPrint = true
            encodeDefaults = true // include fields even when they equal their default (e.g. subTopicId = null)
        }

        val sourceCode = buildString {
            appendLine("// Auto-generated backup — ${questions.size} questions exported from Firestore")
            appendLine("// DO NOT hand-edit large sections of this file; regenerate instead if the source data changes.")
            appendLine()
            appendLine("package com.anuram.app.data.backup")
            appendLine()
            appendLine("import com.anuram.app.data.Question")
            appendLine("import com.google.firebase.Timestamp")
            appendLine()
            appendLine("listOf(")
            questions.forEachIndexed { index, q ->
                val comma = if (index != questions.lastIndex) "," else ""
                appendLine("    ${q.toKotlinCode().replace("\n", "\n    ")}$comma")
            }
            appendLine(")")
        }

        val exportDir = File(context.getExternalFilesDir(null), "exports").apply { mkdirs() }
        val outputFile = File(exportDir, outputFileName)

        outputFile.writeText(json.encodeToString(sourceCode))

        outputFile
    }

    private fun mapDocumentSafely(doc: DocumentSnapshot): Question {
//    return Question(
//        id = doc.id,
//        answer = doc.get("answer").toString().toInt() ?: -1, // -1 = visibly "needs review", not a valid option index
//        explanation = doc.get("explanation").toString() ?: "",
//        explanation_ta = doc.get("explanation_ta").toString() ?: "",
//        group = doc.get("group").toString() ?: "",
//        number = doc.get("number").toString().toInt() ?: 0,
//        options_en = doc.get("options_en") as List<String>,
//        options_ta = doc.get("options_ta") as List<String>,
//        practiceType = doc.get("practiceType").toString() ?: "",
//        q_en = doc.get("q_en").toString() ?: "",
//        q_ta = doc.getString("q_ta") ?: "",
//        subject = doc.getString("subject") ?: "",
//        subtopic = doc.getString("subtopic") ?: "",
//        time = doc.get("time") as Timestamp,
//        unit = doc.getString("unit") ?: "",
//        year = (doc.get("year") ?: "") as String // handles year stored as EITHER a String or a Long
//    )
        return  Question(
            id = doc.id,
            answer = doc.safeInt("answer") ?: -1, // -1 = visibly "needs review", not a valid option index
            explanation = doc.safeString("explanation") ?: "",
            explanation_ta = doc.safeString("explanation_ta") ?: "",
            group = doc.safeString("group") ?: "",
            number = doc.safeInt("number") ?: 0,
            options_en = doc.safeStringList("options_en"),
            options_ta = doc.safeStringList("options_ta"),
            practiceType = doc.safeString("practiceType") ?: "",
            q_en = doc.safeString("q_en") ?: "",
            q_ta = doc.safeString("q_ta") ?: "",
            subject = doc.safeString("subject") ?: "",
            subtopic = doc.safeString("subtopic") ?: "",
//            time = doc.safeTimestamp("time"),
            unit = doc.safeString("unit") ?: "",
            year = doc.safeYearAsString("year") // handles year stored as EITHER a String or a Long
        )
}
    private fun mapDocumentSafelyNew(doc: DocumentSnapshot): NewQuestion {
//    return Question(
//        id = doc.id,
//        answer = doc.get("answer").toString().toInt() ?: -1, // -1 = visibly "needs review", not a valid option index
//        explanation = doc.get("explanation").toString() ?: "",
//        explanation_ta = doc.get("explanation_ta").toString() ?: "",
//        group = doc.get("group").toString() ?: "",
//        number = doc.get("number").toString().toInt() ?: 0,
//        options_en = doc.get("options_en") as List<String>,
//        options_ta = doc.get("options_ta") as List<String>,
//        practiceType = doc.get("practiceType").toString() ?: "",
//        q_en = doc.get("q_en").toString() ?: "",
//        q_ta = doc.getString("q_ta") ?: "",
//        subject = doc.getString("subject") ?: "",
//        subtopic = doc.getString("subtopic") ?: "",
//        time = doc.get("time") as Timestamp,
//        unit = doc.getString("unit") ?: "",
//        year = (doc.get("year") ?: "") as String // handles year stored as EITHER a String or a Long
//    )


        return NewQuestion(
            answer = doc.safeInt("answer") ?: -1, // -1 = visibly "needs review", not a valid option index
            explanation = doc.safeString("explanation") ?: "",
            explanationTamil = doc.safeString("explanation_ta") ?: "",
            qno = doc.safeInt("number") ?: 0,
            options = doc.safeStringList("options_en"),
            optionsTamil = doc.safeStringList("options_ta"),
            question = doc.safeString("q_en") ?: "",
            questionTamil = doc.safeString("q_ta") ?: "",
            mainTopic = if((doc.safeString("id") ?: "").startsWith("Previous Year Questions")) "pyq"
            else if((doc.safeString("id") ?: "").startsWith("generalTamil")) "tamil"
            else "aptitude",
            subtopic = if(!doc.safeString("group").isNullOrEmpty()) doc.safeString("group") ?: ""
            else if((doc.safeString("id") ?: "").startsWith("generalTamil")) doc.safeString("unit") ?: ""
            else "",
            subCollection = if(!doc.safeString("unit").isNullOrEmpty()
                && !doc.safeString("subTopic").isNullOrEmpty()
                && doc.safeString("year").isNullOrEmpty()) doc.safeString("subTopic") ?: ""
            else doc.safeString("year") ?: "",
            premium = (doc.safeInt("number") ?: 0) > 10
        )
}

    private fun DocumentSnapshot.safeString(field: String): String? =
        try { getString(field) } catch (e: Exception) { null }

    private fun DocumentSnapshot.safeInt(field: String): Int? =
        try {
            getLong(field)?.toInt()
                ?: (get(field) as? String)?.toIntOrNull() // handles a numeric field stored as a String on old docs
        } catch (e: Exception) { null }

    private fun DocumentSnapshot.safeStringList(field: String): List<String> =
        try {
            (get(field) as? List<*>)?.mapNotNull { it as? String } ?: emptyList()
        } catch (e: Exception) { emptyList() }

    private fun DocumentSnapshot.safeTimestamp(field: String): Timestamp =
        try { getTimestamp(field) ?: Timestamp(0, 0) } catch (e: Exception) { Timestamp(0, 0) }

    /**
     * `year` is a String in the Question model, but based on your earlier
     * screenshot ("year: 2022" as a plain number), some legacy documents
     * likely store it as a Firestore number instead. This reads either
     * shape and coerces it to a String either way, instead of crashing
     * or silently dropping the value when the stored type doesn't match
     * the model's declared type.
     */
    private fun DocumentSnapshot.safeYearAsString(field: String): String =
        try {
            getString(field) // already a String on this document
                ?: getLong(field)?.toString() // stored as a number — coerce to String
                ?: ""
        } catch (e: Exception) {
            // getString()/getLong() can each throw if the field exists but
            // is neither shape (e.g. a Timestamp by mistake) — fall back safely.
            get(field)?.toString() ?: ""
        }


    private fun String.toKotlinStringLiteral(): String {
        val escaped = this
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("$", "\\$")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
        return "\"$escaped\""
    }

    private fun List<String>.toKotlinListLiteral(): String =
        "listOf(${joinToString(", ") { it.toKotlinStringLiteral() }})"

    private fun Timestamp.toKotlinLiteral(): String =
        "Timestamp(${seconds}L, $nanoseconds)"

    /**
     * Renders a single Question as a Kotlin constructor call, e.g.:
     *
     * Question(
     *     id = "abc123",
     *     answer = 2,
     *     ...
     * )
     */
    private fun Question.toKotlinCode(indent: String = "    "): String {
        return buildString {
            appendLine("Question(")
            appendLine("$indent    id = ${id.toKotlinStringLiteral()},")
            appendLine("$indent    answer = $answer,")
            appendLine("$indent    explanation = ${explanation.toKotlinStringLiteral()},")
            appendLine("$indent    explanation_ta = ${explanation_ta.toKotlinStringLiteral()},")
            appendLine("$indent    group = ${group.toKotlinStringLiteral()},")
            appendLine("$indent    number = $number,")
            appendLine("$indent    options_en = ${options_en.toKotlinListLiteral()},")
            appendLine("$indent    options_ta = ${options_ta.toKotlinListLiteral()},")
            appendLine("$indent    practiceType = ${practiceType.toKotlinStringLiteral()},")
            appendLine("$indent    q_en = ${q_en.toKotlinStringLiteral()},")
            appendLine("$indent    q_ta = ${q_ta.toKotlinStringLiteral()},")
            appendLine("$indent    subject = ${subject.toKotlinStringLiteral()},")
            appendLine("$indent    subtopic = ${subtopic.toKotlinStringLiteral()},")
//            appendLine("$indent    time = ${time.toKotlinLiteral()},")
            appendLine("$indent    unit = ${unit.toKotlinStringLiteral()},")
            appendLine("$indent    year = ${year.toKotlinStringLiteral()}")
            append("$indent)")
        }
    }
    private fun NewQuestion.toKotlinCode(indent: String = "    "): String {
        return buildString {
            appendLine("NewQuestion(")
            appendLine("$indent    answer = $answer,")
            appendLine("$indent    explanation = ${explanation.toKotlinStringLiteral()},")
            appendLine("$indent    explanationTamil = ${explanationTamil.toKotlinStringLiteral()},")
            appendLine("$indent    qno = $qno,")
            appendLine("$indent    options = ${options.toKotlinListLiteral()},")
            appendLine("$indent    optionsTamil = ${optionsTamil.toKotlinListLiteral()},")
            appendLine("$indent    question = ${question.toKotlinStringLiteral()},")
            appendLine("$indent    questionTamil = ${questionTamil.toKotlinStringLiteral()},")
            appendLine("$indent    mainTopic = ${mainTopic.toKotlinStringLiteral()},")
            appendLine("$indent    subtopic = ${subtopic.toKotlinStringLiteral()},")
//            appendLine("$indent    time = ${time.toKotlinLiteral()},")
            appendLine("$indent    subCollection = ${subCollection.toKotlinStringLiteral()},")
            appendLine("$indent    isPremium = $premium")
            append("$indent)")
        }
    }


    suspend fun readQuestionsFromJsonFile(file: File): List<NewQuestion> = withContext(Dispatchers.IO) {
        val jsonArray = JSONArray(file.readText())
        val questions = mutableListOf<NewQuestion>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            questions.add(obj.toNewQuestion())
        }

        questions
    }

    private fun JSONObject.toQuestion(): Question {
        return Question(
            id = optString("id", ""),
            answer = optInt("answer", -1),
            explanation = optString("explanation", ""),
            explanation_ta = optString("explanation_ta", ""),
            group = optString("group", ""),
            number = optInt("number", 0),
            options_en = optStringList("options_en"),
            options_ta = optStringList("options_ta"),
            practiceType = optString("practiceType", ""),
            q_en = optString("q_en", ""),
            q_ta = optString("q_ta", ""),
            subject = optString("subject", ""),
            subtopic = optString("subtopic", ""),
//            time = optTimestampFromMillis("time"),
            unit = optString("unit", ""),
            // By the time this JSON was written, `year` was already coerced
            // to a String during export (safeYearAsString) — so it's always
            // a plain JSON string here, no dual-type handling needed on read.
            year = optString("year", "")
        )
    }


    //
    //            if(!unit.isNullOrEmpty() && !subtopic.isNullOrEmpty() && year.isNullOrEmpty()) {
    //                appendLine("$indent    subCollection = ${subtopic.toKotlinStringLiteral()},")
    //            } else {
    //                appendLine("$indent    subCollection = ${year.toKotlinStringLiteral()},")
    //            }
    ////            appendLine("$indent    time = ${time.toKotlinLiteral()},")
    ////            appendLine("$indent    subCollection = ${year.toKotlinStringLiteral()},")
    //            appendLine("$indent    isPremium = ${number > 10}")
    //
    private fun JSONObject.toNewQuestion(): NewQuestion {
        return NewQuestion(
            answer = optInt("answer", -1),
            explanation = optString("explanation", ""),
            explanationTamil = optString("explanation_ta", ""),
            qno = optInt("number", 0),
            options = optStringList("options_en"),
            optionsTamil = optStringList("options_ta"),
            question = optString("q_en", ""),
            questionTamil = optString("q_ta", ""),
            mainTopic = if(optString("id", "").startsWith("Previous Year Questions")) "pyq"
            else if(optString("id", "").startsWith("generalTamil")) "tamil"
            else "aptitude",
            subtopic = if(!optString("group", "").isNullOrEmpty()) optString("group", "")
            else if(optString("id", "").startsWith("generalTamil")) optString("unit", "")
            else "",
//            time = optTimestampFromMillis("time"),
            // By the time this JSON was written, `year` was already coerced
            // to a String during export (safeYearAsString) — so it's always
            // a plain JSON string here, no dual-type handling needed on read.
            subCollection = if(!optString("unit", "").isNullOrEmpty()
                && !optString("subtopic", "").isNullOrEmpty()
                && optString("year", "").isNullOrEmpty()) optString("subtopic", "")
            else optString("year", ""),
        )
    }

    private fun JSONObject.optStringList(field: String): List<String> {
        val arr = optJSONArray(field) ?: return emptyList()
        return (0 until arr.length()).map { arr.getString(it) }
    }

}