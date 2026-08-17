package com.kumar.crackup.util

import android.content.Context
import com.kumar.crackup.model.NewQuestion
import com.kumar.crackup.model.Question
import com.kumar.crackup.model.toNewQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import kotlin.collections.emptyList

object JSONToKotlinConverter {

    suspend fun convertJsonFileToKotlinFile(
        context: Context,
        jsonFile: File,
        outputFileName: String = "QuestionsEditable.kt",
        listVariableName: String = "backupQuestions"
    ): File = withContext(Dispatchers.IO) {
        val jsonArray = JSONArray(jsonFile.readText())
        val questions = mutableListOf<Question>()

        for (i in 0 until jsonArray.length()) {
            questions.add(jsonArray.getJSONObject(i).toQuestion())
        }

        val sourceCode = buildString {
            appendLine("// Editable backup — ${questions.size} questions")
            appendLine("package com.anuram.app.data.backup")
            appendLine()
            appendLine("import com.anuram.app.data.Question")
            appendLine("import com.google.firebase.Timestamp")
            appendLine()
            appendLine("val $listVariableName = listOf(")
            questions.forEachIndexed { index, q ->
                val comma = if (index != questions.lastIndex) "," else ""
                appendLine("    ${q.toKotlinCodeNew().replace("\n", "\n    ")}$comma")
            }
            appendLine(")")
        }

        val exportDir = File(context.getExternalFilesDir(null), "exports").apply { mkdirs() }
        val outputFile = File(exportDir, outputFileName)
        outputFile.writeText(sourceCode)

        outputFile
    }

// ============================================================
// JSON -> Question (same defensive reading as before)
// ============================================================

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
            year = optString("year", "")
        )
    }

    private fun JSONObject.optStringList(field: String): List<String> {
        val arr = optJSONArray(field) ?: return emptyList()
        return (0 until arr.length()).map { arr.getString(it) }
    }

//    private fun JSONObject.optTimestampFromMillis(field: String): Timestamp {
//        val millis = optLong(field, 0L)
//        return Timestamp(millis / 1000, ((millis % 1000) * 1_000_000).toInt())
//    }

// ============================================================
// Question -> Kotlin source (same escaping as before)
// ============================================================

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

//    private fun Timestamp.toKotlinLiteral(): String =
//        "Timestamp(${seconds}L, $nanoseconds)"

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
    private fun Question.toKotlinCodeNew(indent: String = "    "): String {
        return buildString {
            appendLine("NewQuestion(")
            appendLine("$indent    answer = $answer,")
            appendLine("$indent    explanation = ${explanation.toKotlinStringLiteral()},")
            appendLine("$indent    explanationTamil = ${explanation_ta.toKotlinStringLiteral()},")
            appendLine("$indent    qNo = $number,")
            appendLine("$indent    options = ${options_en.toKotlinListLiteral()},")
            appendLine("$indent    optionsTamil = ${options_ta.toKotlinListLiteral()},")
            appendLine("$indent    practiceType = ${practiceType.toKotlinStringLiteral()},")
            appendLine("$indent    question = ${q_en.toKotlinStringLiteral()},")
            appendLine("$indent    questionTamil = ${q_ta.toKotlinStringLiteral()},")

            if(id.startsWith("Previous Year Questions")) {
                appendLine("$indent    mainTopic = \"pyq\",")
            } else if(id.startsWith("generalTamil")) {
                appendLine("$indent    mainTopic = \"tamil\",")
            } else {
                appendLine("$indent    mainTopic = \"aptitude\",")
            }

            if(!group.isNullOrEmpty()) {
                appendLine("$indent    subtopic = ${group.toKotlinStringLiteral()},")
            } else if(id.startsWith("generalTamil")){
                appendLine("$indent    subtopic = ${unit.toKotlinStringLiteral()},")
            } else {
                appendLine("$indent    subtopic = \"\",")
            }

            if(!unit.isNullOrEmpty() && !subtopic.isNullOrEmpty() && year.isNullOrEmpty()) {
                appendLine("$indent    subCollection = ${subtopic.toKotlinStringLiteral()},")
            } else {
                appendLine("$indent    subCollection = ${year.toKotlinStringLiteral()},")
            }
//            appendLine("$indent    time = ${time.toKotlinLiteral()},")
//            appendLine("$indent    subCollection = ${year.toKotlinStringLiteral()},")
            appendLine("$indent    isPremium = ${number > 10}")
            append("$indent)")
        }
    }

//    fun convertToNewModel(): List<NewQuestion> {
//        val questions = backupQuestions
//        val newQuestions = mutableListOf<NewQuestion>()
//        questions.forEach {
//            newQuestions.add(it.toNewQuestion())
//        }
//        return newQuestions
//    }
}