package com.kumar.crackup.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonPrimitive
import javax.annotation.PropertyKey


//docid = mainTopic + _ + subTopic + _ + subCollection
@Serializable
data class NewQuestion(
    val answer: Int = 0,
    val explanation: String = "",
    val explanationTamil: String = "",
    val mainTopic: String = "pyq", //:"tamil" //general science
    val qno: Int = 0,
    @get:PropertyName("options")
    @set:PropertyName("options")
    var rawOptions: List<@Contextual Any>? = null,
    @get:PropertyName("optionsTamil")
    @set:PropertyName("optionsTamil")
    var rawOptionsTamil: List<@Contextual Any>? = null,
    val question: String = "",
    val questionTamil: String = "",
    val subtopic: String = "exam_g1",
    val subCollection: String = "2019",
    val premium: Boolean = false,
) {
    @get:Exclude
    val options : List<String>
        get() = rawOptions?.mapNotNull { it?.toString() } ?: emptyList()

//        get() = rawOptions?.mapNotNull { it.jsonPrimitive.content } ?: emptyList()

    @get:Exclude
    val optionsTamil : List<String>
        get() = rawOptionsTamil?.mapNotNull { it.toString() } ?: emptyList()
//        get() = rawOptionsTamil?.mapNotNull { it.jsonPrimitive.content } ?: emptyList()
}


fun Question.toNewQuestion() : NewQuestion{
    return NewQuestion(
        answer = this.answer,
        explanation = this.explanation,
        explanationTamil = this.explanation_ta,
        qno = this.number,
//        options = this.options_en,
//        optionsTamil = this.options_ta,
        question = this.q_en,
        questionTamil = this.q_ta,

        mainTopic = if(this.id.startsWith("Previous Year Questions"))
            "pyq"
        else if(this.id.startsWith("generalTamil"))
            "tamil"
        else "aptitude",
        subtopic = if(!this.group.isNullOrEmpty())
            this.group
        else this.unit,
        subCollection = if(!this.unit.isNullOrEmpty() && !this.subtopic.isNullOrEmpty() && this.year.isNullOrEmpty())
            this.subtopic
        else this.year,
        premium = this.number > 10
    )
}
