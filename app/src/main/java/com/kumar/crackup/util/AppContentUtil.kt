package com.kumar.crackup.util

import com.kumar.crackup.model.DayStreak
import com.kumar.crackup.model.FeatureItem
import com.kumar.crackup.model.LocalizedInstruction
import com.kumar.crackup.model.ServiceModel
import com.kumar.crackup.model.StatItem
import com.kumar.crackup.model.TopicProgress
import com.kumar.crackup.model.WeekStreak

object AppContentUtil {

    val stats = listOf(
        StatItem(
            stat = "10K+",
            description = "MCQs"
        ),
        StatItem(
            stat = "3K+",
            description = "PYQs"
        ),
        StatItem(
            stat = "24/7",
            description = "Access"
        )
    )


    val examInstructions = listOf(
        LocalizedInstruction(
            englishRule = "Read all questions carefully before answering",
            tamilRule = "ஒவ்வொரு வினாவையும் கவனமாக படிக்கவும்",
        ),
        LocalizedInstruction(
            englishRule = "Select only one correct option",
            tamilRule = "ஒரே ஒரு சரியான விடையை தேர்வு செய்யவும்",
        ),
        LocalizedInstruction(
            englishRule = "Use Mark for Review for doubtful questions",
            tamilRule = "சந்தேகமான வினாக்களுக்கு Mark for Review பயன்படுத்தலாம்",
        ),
        LocalizedInstruction(
            englishRule = "Do not refresh or close browser during exam",
            tamilRule = "தேர்வின்போது Refresh செய்ய வேண்டாம்",
        ),
        LocalizedInstruction(
            englishRule = "Switching tabs may auto-submit the test",
            tamilRule = "Tab மாற்றினால் தேர்வு auto submit ஆகலாம்",
        ),
        LocalizedInstruction(
            englishRule = "Stable internet connection is recommended",
            tamilRule = "இணைய இணைப்பு நிலையாக இருக்க வேண்டும்",
        ),
        LocalizedInstruction(
            englishRule = "Submit only after completing all questions",
            tamilRule = "அனைத்து வினாக்களையும் முடித்த பின் submit செய்யவும்",
        )
    )

    val servicesList = listOf(
        ServiceModel(
            title = "🔥 Full Package",
            category = "Complete TNPSC preparation bundle",
            includedFeatures = listOf(
                "General Tamil/English",
                "General Studies",
                "Aptitude",
                "PYQs"
            ),
            price = "₹ 1,499"
        ),
        ServiceModel(
            title = "📘 General Tamil / English",
            category = "Grammar, vocabulary & practice tests",
            includedFeatures = listOf(
                "Tamil/English"
            ),
            eligibleFor = listOf("tamil"),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "🌍 General Studies",
            category = "History, Geography, Polity & Science",
            includedFeatures = listOf(
                "General Studies"
            ),
            eligibleFor = listOf(
                "science",
                "geography",
                "history_india",
                "polity",
                "economy",
                "history_tamilnadu"
            ),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "🧠 Aptitude & Reasoning",
            category = "Mental ability, reasoning & shortcut methods",
            includedFeatures = listOf(
                "Aptitude"
            ),
            eligibleFor = listOf("aptitude"),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "📄 PYQ Package",
            category = "Previous year TNPSC questions with explanations",
            includedFeatures = listOf(
                "PYQs of Group |, || / ||A, IV"
            ),
            eligibleFor = listOf("pyq"),
            price = "₹ 299"
        )

    )

    val features =
        listOf(
            FeatureItem(
                emoji = "📚",
                title = "Daily MCQs",
                subTitle = "Practice topic-wise TNPSC questions daily"
            ),
            FeatureItem(
                emoji = "🌐",
                title = "Tamil & English",
                subTitle = "Learn comfortably in your own language"
            ),
            FeatureItem(
                emoji = "🎯",
                title = "PYQ Practice",
                subTitle = "Previous year TNPSC questions with explanations"
            ),
            FeatureItem(
                emoji = "📺",
                title = "Free Classes",
                subTitle = "Watch free TNPSC revision classes on YouTube"
            ),
            FeatureItem(
                emoji = "📰",
                title = "Current Affairs",
                subTitle = "Daily & Monthly current affair updates"
            ),
            FeatureItem(
                emoji = "🏆",
                title = "Mock Tests",
                subTitle = "Improve speed and accuracy with practice tests"
            )
        )

    val dayStreaks = listOf(
        DayStreak(
            label = "Monday",
            completed = true
        ),
        DayStreak(
            label = "Tuesday",
            completed = true
        ),
        DayStreak(
            label = "Wednesday",
            completed = true
        ),
        DayStreak(
            label = "Thursday",
            completed = false
        ),
        DayStreak(
            label = "Friday",
            completed = false
        ),
        DayStreak(
            label = "Saturday",
            completed = false
        ),
        DayStreak(
            label = "Sunday",
            completed = false
        ),

        )

    val weekStreaks = listOf(
        WeekStreak(
            label = "",
            daysCompleted = 4,
        ),
        WeekStreak(
            label = "",
            daysCompleted = 6,
        ),
        WeekStreak(
            label = "",
            daysCompleted = 3,
        )
    )

    val topicsProgress = listOf(
        TopicProgress(
            name = "Previous Year Questions",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "General Tamil",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "General Science",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Geography",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Indian History",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Indian Polity",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Indian Economy and Development",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Tamil Nadu History",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        ),
        TopicProgress(
            name = "Aptitude",
            completedPercent = .37f,
            accuracyPercent = .85f,
            questionsDone = 37,
            questionsTotal = 100
        )

    )
}