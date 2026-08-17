package com.kumar.crackup.model

import kotlinx.serialization.Serializable


//docid = mainTopic + _ + subTopic + _ + subCollection
@Serializable
data class NewQuestion(
    val answer: Int = 0,
    val explanation: String = "",
    val explanationTamil: String = "",
    val mainTopic: String = "pyq", //:"tamil" //general science
    val qno: Int = 0,
    val options: List<String> = emptyList(),
    val optionsTamil: List<String> = emptyList(),
    val question: String = "",
    val questionTamil: String = "",
    val subtopic: String = "exam_g1",
    val subCollection: String = "2019",
    val premium: Boolean = false,
)


fun Question.toNewQuestion() : NewQuestion{
    return NewQuestion(
        answer = this.answer,
        explanation = this.explanation,
        explanationTamil = this.explanation_ta,
        qno = this.number,
        options = this.options_en,
        optionsTamil = this.options_ta,
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
