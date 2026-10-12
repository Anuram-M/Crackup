package com.kumar.crackup.util

import com.kumar.crackup.currentaffairs.CACategoryModel
import com.kumar.crackup.dailyChallenge.DailyChallengeModel
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

    const val HOME_ROUTE = "home?targetSection=header"
    const val HOME_FROM_PROFILE_ROUTE = "home?targetSection=stickyHeader"
    val currentAffairsCategories = listOf(
        CACategoryModel(
            category = "ca_daily",
            emoji = "📰",
            title = "Daily Current Affairs",
            subTitle = "Check our daily current affairs with detailed explanations."
        ),
        CACategoryModel(
            category = "ca_monthly",
            emoji = "📅",
            title = "Monthly Current Affairs",
            subTitle = "Check our monthly current affairs with detailed explanations."
        ),
        CACategoryModel(
            category = "ca_quiz",
            emoji = "🏆",
            title = "Current Affairs Quiz",
            subTitle = "Take a quiz to test your knowledge of current affairs of Tamil Nadu and India"
        ),
    )

    val challenge = DailyChallengeModel(
        answer = 3,
        explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
        explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
        options = listOf("3", "4", "5", "6"),
        optionsTamil = listOf("3", "4", "5", "6"),
        question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
        questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
    )
    val dailyChallenge = listOf(
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),
        DailyChallengeModel(
            answer = 3,
            explanation = "<p>In 2016, ISRO successfully launched 6 Indian operational satellites, including navigation satellites like IRNSS-1E, 1F, and 1G, Cartosat-2 Series, INSAT-3DR, and Resourcesat-2A.</p>\n",
            explanationTamil = "<p>2016 ஆம் ஆண்டில், இஸ்ரோ (ISRO) வழிகாட்டு செயற்கைக்கோள்களான IRNSS-1E, 1F, 1G மற்றும் கார்டோசாட்-2, இன்சாட்-3DR, ரிசோர்சாட்-2A உட்பட மொத்தம் 6 இந்திய செயற்கைக்கோள்களை வெற்றிகரமாக விண்ணில் ஏவியது.</p>\n",
            options = listOf("3", "4", "5", "6"),
            optionsTamil = listOf("3", "4", "5", "6"),
            question = "<p>During the year 2016, total number of Indian Satellites launched by ISRO is</p>\n",
            questionTamil = "<p>2016 ஆம் ஆண்டில் ISRO-வால் விண்ணில் ஏவப்பட்ட இந்திய செயற்கைகோள்களின் எண்ணிக்கையானது</p>\n",
        ),

    )
}