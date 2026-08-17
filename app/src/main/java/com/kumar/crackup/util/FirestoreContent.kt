package com.kumar.crackup.util

import com.kumar.crackup.model.SubTopic
import com.kumar.crackup.model.TamilUnit

object FirestoreContent {

    val tamilUnits = listOf(
        TamilUnit(
            name = "Unit I – இலக்கணம்",
            nameTamil = "Unit I – இலக்கணம்",
            unitTopics = listOf(
                "பிரித்து எழுதுதல் - சேர்த்து எழுதுதல்",
                "சந்திப்பிழை",
                "குறில் - நெடில் வேறுபாடு",
                "லகர, ளகர, ழகர வேறுபாடு",
                "னகர, ணகர வேறுபாடு",
                "ரகர, றகர வேறுபாடு",
                "இனவெழுத்துகள்",
                "சுட்டு எழுத்துகள்",
                "வினா எழுத்துகள்",
                "ஒருமை - பன்மை",
                "வேர்ச்சொல்",
                "வினைமுற்று",
                "வினையெச்சம்",
                "பெயரெச்சம்",
                "எழுத்துப் பிழை / ஒற்றுப்பிழை",
            ),
            unitTopicsEnglish = listOf(
                "piriththu-serththu",
                "santhipizhai",
                "kuril - nedil verubadu",
                "lagara, lagara, zhagara verubadu",
                "nagara, nagara verubadu",
                "ragara, ragara verubadu",
                "inavezhuthukal",
                "suttu ezhuthukal",
                "vinai ezhuthukal",
                "orumai - panmai",
                "vercholl",
                "vinai mutru",
                "vinaiyechcham",
                "peyarechcham",
                "ezhuthup pizhai / otrup pizhai"
            ),
            order = 0,
            unitName = "ilakkanam"
        ),
        TamilUnit(
            name = "Unit II – சொல்லகராதி",
            nameTamil = "Unit II – சொல்லகராதி",
            unitTopics = listOf(
                "எதிர்ச்சொல்",
                "ஒரெழுத்து ஒரு மொழி",
                "உரிய பொருள் கண்டறிதல்",
                "ஒருபொருள் பல சொற்கள்",
                "பொருந்தா சொல்",
                "அகர வரிசை",
                "ஒருபொருள் பன்மொழி",
                "இருபொருள் சொற்கள்",
                "பேச்சு வழக்கு- எழுத்து வழக்கு",
                "பிழை திருத்தம்",
            ),
            unitTopicsEnglish = listOf(
                "ethircholl",
                "orezhuthu oru mozhi",
                "uriya porul kandarithal",
                "oruporul pala sorkal",
                "poruntha choll",
                "agara varisai",
                "oruporul panmozhi",
                "iruporul sorkal",
                "pechu valakku - ezhuthu valakku",
                "pizhai thirutham"
            ),
            order = 1,
            unitName = "solagarathy"
        ),
        TamilUnit(
            name = "Unit III - எழுதும் திறன்",
            nameTamil = "Unit III - எழுதும் திறன்",
            unitTopics = listOf(
                "சொற்களை ஒழுங்குபடுத்துதல்",
                "செய்வினை / செயப்பாட்டு வினை",
                "தன்வினை / பிறவினை",
                "ஒருமை - பன்மை பிழை",
                "திணை மரபு",
                "பால் மரபு",
                "காலம்",
                "நிறுத்தல் குறியீடுகள்",
            ),
            unitTopicsEnglish = listOf(
                "sorkalai ozhungupaduthuthal",
                "seivinai / seyappattu vinai",
                "thanvinai / piravinai",
                "orumai - panmai pizhai",
                "thinai marabu",
                "paal marabu",
                "kaalam",
                "niruthal kuriyeedugal"
            ),
            order = 2,
            unitName = "ezhuthum_thiran"
        ),
        TamilUnit(
            name = "Unit IV - கலைச் சொற்கள்",
            nameTamil = "Unit IV - கலைச் சொற்கள்",
            unitTopics = listOf(
                "பல்துறை சார்ந்த கலைச்சொற்கள்",
            ),
            unitTopicsEnglish = listOf(
                "palathurai saarntha kalaichorkal"
            ),
            order = 3,
            unitName = "kalai sorkkal"
        ),
        TamilUnit(
            name = "Unit V- வாசித்தல் - புரிந்து கொள்ளும் திறன்",
            nameTamil = "Unit V- வாசித்தல் - புரிந்து கொள்ளும் திறன்",
            unitTopics = listOf(
                "பத்தியிலிருந்து விடை",
                "உவமைத் தொடர்",
                "மரபுத் தொடர்",
                "பழமொழிகள்",
                "ஆவணப் புரிதல்",
            ),
            unitTopicsEnglish = listOf(
                "pathiyilirunthu vidai",
                "uvamai thodar",
                "marabu thodar",
                "pazhamozhigal",
                "aavanap purithal"
            ),
            order = 4,
            unitName = "vasiththal"
        ),
        TamilUnit(
            name = "Unit VI - எளிய மொழி பெயர்ப்பு",
            nameTamil = "Unit VI - எளிய மொழி பெயர்ப்பு",
            unitTopics = listOf(
                "ஆங்கிலச் சொற்களுக்கு இணையான தமிழ்ச் சொற்கள்",
            ),
            unitTopicsEnglish = listOf(
                "aangila sorkalukku inaiyaana thamizh sorkal"
            ),
            order = 5,
            unitName = "mozhi_peyarppu"
        ),
        TamilUnit(
            name = "Unit VII – இலக்கியம், தமிழ் அறிஞர்களும், தமிழ்த்தொண்டும்",
            nameTamil = "Unit VII – இலக்கியம், தமிழ் அறிஞர்களும், தமிழ்த்தொண்டும்",
            unitTopics = listOf(
                "திருக்குறள் தொடர்பான செய்திகள்",
                "நாலடியார்",
                "நான்மணிக்கடிகை",
                "பழமொழி நானூறு",
                "முதுமொழிக்காஞ்சி",
                "திரிகடுகம்",
                "இன்னாநாற்பது",
                "சிறுபஞ்சமூலம்",
                "ஏலாதி",
                "அவ்வையார் பாடல்கள்",
                "தமிழின் தொன்மை, சிறப்பு, திராவிட மொழிகள் தொடர்பான செய்திகள்",
                "உ.வே. சாமிநாத ஐயர்",
                "தெ.பொ. மீனாட்சி சுந்தரம்",
                "சி. இலக்குவனார்",
                "தேவநேய பாவாணர்",
                "அகரமுதலி",
                "பாவலரேறு பெருஞ்சித்திரனார்",
                "ஜி.யு.போப்",
                "வீரமாமுனிவர்",
                "பாவேந்தர்",
                "டி.கே. சிதம்பரனாதர்",
                "தவத்திரு குன்றக்குடி அடிகளார்",
                "கண்ணதாசன்",
                "காயிதே மில்லத்",
                "தாரா பாரதி",
                "வேலுநாச்சியார்",
                "பட்டுக்கோட்டைக் கல்யாணசுந்தரம்",
                "முடியரசன்",
                "தமிழ் ஒளி",
                "உருத்திரங்கண்ணனார்",
                "கி. வா. ஜகந்நாதர்",
                "நாமக்கல் கவிஞர்",
            ),
            unitTopicsEnglish = listOf(
                "thirukkural thodarpana seithigal",
                "naaladiyar",
                "naanmanikkadigai",
                "pazhamozhi naanooru",
                "muthumozhikkanji",
                "thirigadugam",
                "inna naarpathu",
                "sirupanjamoolam",
                "elaathi",
                "avvaiyar paadalgal",
                "thamizhin thonmai, sirappu, thiraavida mozhigal thodarpana seithigal",
                "u. ve. swaminatha iyer",
                "the. po. meenakshi sundaram",
                "si. ilakkuvanaar",
                "devaneya paavaanar",
                "agaramuthali",
                "paavalareru perunchithiranaar",
                "g. u. pope",
                "veeramamaamunivar",
                "paaventhar",
                "t. k. chidambaranathar",
                "thavathiru kundrakudi adigalar",
                "kannadhasan",
                "kayithe millath",
                "thara bharathi",
                "velunachiyar",
                "pattukkottai kalyanasundaram",
                "mudiyarasan",
                "tamil oli",
                "uruthirangannanar",
                "ki. va. jagannathan",
                "namakkal kavignar"
            ),
            order = 6,
            unitName = "ilakkiyam"
        ),
    )

    val subTopics = listOf(
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Scientific knowledge and scientific temper",
            nameTamil = "அறிவியல் அறிவு மற்றும் அறிவியல் உணர்வு",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Power of reasoning",
            nameTamil = "பகுத்தறிதல்",
            order = 1
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Rote learning vs conceptual learning",
            nameTamil = "பொருள் உணராமல் கற்றலும் கருத்துணர்ந்து கற்றலும்",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Science as a tool to understand the past, present, and future",
            nameTamil = "கடந்தகாலம், நிகழ்காலம், எதிர்காலம் பற்றி புரிந்து கொள்வதற்கான ஒரு கருவி அறிவியல்",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Nature of universe",
            nameTamil = "பேரண்டத்தின் இயல்பு",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "General scientific laws",
            nameTamil = "பொது அறிவியல் விதிகள்",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Mechanics",
            nameTamil = "இயக்கவியல்",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Properties of matter, force, motion, and energy",
            nameTamil = "பருப்பொருளின் பண்புகள், விசை, இயக்கம் மற்றும் ஆற்றல்",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Everyday application of mechanics",
            nameTamil = "அன்றாட வாழ்வில் இயக்கவியல்",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Electricity and magnetism",
            nameTamil = "மின்னியல், காந்தவியல்",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Light",
            nameTamil = "ஒளி",
            subtopicQuery = "",
            order = 10
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Sound",
            nameTamil = "ஒலி",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Heat",
            nameTamil = "வெப்பம்",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Nuclear physics",
            nameTamil = "அணுக்கரு இயற்பியல்",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Laser, electronics, and communications",
            nameTamil = "லேசர், மின்னணுவியல் மற்றும் தகவல் தொடர்பியல்",
            subtopicQuery = "",
            order = 14
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Elements and compounds, acids, bases, salts, petroleum products, fertilizers, pesticides",
            nameTamil = "தனிமங்களும் சேர்மங்களும், அமிலங்கள், காரங்கள், உப்புகள், பெட்ரோலிய பொருட்கள், உரங்கள், பூச்சிக்கொல்லிகள்",
            subtopicQuery = "",
            order = 15
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Main concepts of life science",
            nameTamil = "உயிரியலின் முக்கிய கோட்பாடுகள்",
            subtopicQuery = "",
            order = 16
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Classification, evolution, genetics, physiology",
            nameTamil = "உயிர் உலகின் வகைப்பாடு, பரிணாமம், மரபியல், உடலியங்கியல்",
            subtopicQuery = "",
            order = 17
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Nutrition, health and hygiene, human diseases",
            nameTamil = "உணவியல், உடல்நலம் மற்றும் சுகாதாரம், மனிதநோய்கள்",
            subtopicQuery = "",
            order = 18
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Environment and ecology",
            nameTamil = "சுற்றுப்புறச்சூழல் மற்றும் சூழலியல்",
            subtopicQuery = "",
            order = 19
        ),
        SubTopic(
            topicId = "BkMcKomYdpWHoZMIxJie",
            name = "Latest inventions in science and technology",
            nameTamil = "அறிவியல் மற்றும் தொழில்நுட்பத்தில் அண்மைக்கால கண்டுபிடிப்புகள்",
            subtopicQuery = "",
            order = 20
        ),

//geography
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Location",
            nameTamil = "அமைவிடம்",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Physical features",
            nameTamil = "இயற்கை அமைவுகள்",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Monsoon, rainfall, weather and climate",
            nameTamil = "பருவமழை, மழைப்பொழிவு, வானிலை மற்றும் காலநிலை",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Water resources, Rivers in India",
            nameTamil = "நீர் வளங்கள், இந்திய ஆறுகள்",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Soil, Minerals and Natural Resources, Forest and Wildlife",
            nameTamil = "மண், கனிம வளங்கள் மற்றும் இயற்கை வளங்கள், காடு மற்றும் வன உயிரினங்கள்",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Agricultural pattern",
            nameTamil = "வேளாண் முறைகள்",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Transport",
            nameTamil = "போக்குவரத்து",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Communication",
            nameTamil = "தகவல் தொடர்பு",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Social Geography – Population density and distribution",
            nameTamil = "சமூகப் புவியியல் – மக்கள்தொகை அடர்த்தி மற்றும் பரவல்",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Racial, linguistic groups and major tribes",
            nameTamil = "இனம், மொழிக் குழுக்கள் மற்றும் முக்கியப் பழங்குடிகள்",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Natural calamity and Disaster management",
            nameTamil = "இயற்கைப் பேரிடர் மற்றும் பேரிடர் மேலாண்மை",
            subtopicQuery = "",
            order = 10

        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Environmental pollution – Reasons and preventive measures",
            nameTamil = "சுற்றுச்சூழல் மாசுபடுதல் – காரணங்களும் தடுப்பு முறைகளும்",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Climate change",
            nameTamil = "பருவநிலை மாற்றம்",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Green energy",
            nameTamil = "பசுமை ஆற்றல்",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "Bkz8cT3S6wEEPyKNbUfu",
            name = "Geographical landmarks",
            nameTamil = "புவியியல் அடையாளங்கள்",
            subtopicQuery = "",
            order = 14
        ),



        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Indus Valley Civilization",
            nameTamil = "சிந்து சமவெளி நாகரிகம்",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Guptas",
            nameTamil = "குப்தர்கள்",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Delhi Sultans",
            nameTamil = "தில்லி சுல்தான்கள்",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Mughals",
            nameTamil = "முகலாயர்கள்",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Marathas",
            nameTamil = "மராத்தியர்கள்",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Vijayanagar and Bahmani Kingdoms",
            nameTamil = "விஜயநகர மற்றும் பாமினி அரசுகளின் காலம்",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "South Indian History",
            nameTamil = "தென் இந்திய வரலாறு",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "National Renaissance",
            nameTamil = "தேசிய மறுமலர்ச்சி",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Early uprisings against British rule",
            nameTamil = "ஆங்கிலேயர் ஆட்சிக்கு எதிரான தொடக்க கால எழுச்சிகள்",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Indian National Congress",
            nameTamil = "இந்திய தேசிய காங்கரஸ்",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "B. R. Ambedkar",
            nameTamil = "பி.ஆர்.அம்பேத்கர்",
            subtopicQuery = "",
            order = 10
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Bhagat Singh",
            nameTamil = "பகத்சிங்",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Subash Chandra Bose",
            nameTamil = "சுபாஷ் சந்திர போஸ்",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Mahatma Gandhi",
            nameTamil = "மகாத்மா காந்தி",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Jawaharlal Nehru",
            nameTamil = "ஜவகர்லால் நேரு",
            subtopicQuery = "",
            order = 14
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Kamarajar",
            nameTamil = "காமராசர்",
            subtopicQuery = "",
            order = 15
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Thanthai Periyar",
            nameTamil = "தந்தை பெரியார்",
            subtopicQuery = "",
            order = 16
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Rajaji",
            nameTamil = "இராஜாஜி",
            subtopicQuery = "",
            order = 17
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Rabindranath Tagore",
            nameTamil = "ரவீந்திரநாத் தாகூர்",
            subtopicQuery = "",
            order = 18
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Modes of agitation",
            nameTamil = "விடுதலைப் போராட்டத்தின் பல்வேறு நிலைகள்",
            subtopicQuery = "",
            order = 19
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Satyagraha and Militant Movements",
            nameTamil = "அகிம்சை முறையின் வளர்ச்சி மற்றும் புரட்சிகர இயக்கங்கள்",
            subtopicQuery = "",
            order = 20
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Communalism and Partition",
            nameTamil = "வகுப்புவாதம் மற்றும் தேசப்பிரிவினை",
            subtopicQuery = "",
            order = 21
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Socio-cultural change and continuity in India",
            nameTamil = "இந்திய சமூகப் பண்பாட்டு வரலாற்றில் மாற்றங்களும் தொடர்ச்சியும்",
            subtopicQuery = "",
            order = 22
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Indian Culture and Unity in Diversity",
            nameTamil = "இந்தியப் பண்பாட்டின் இயல்புகள் மற்றும் வேற்றுமையில் ஒற்றுமை",
            subtopicQuery = "",
            order = 23
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "India as a secular state and social harmony",
            nameTamil = "இந்தியா ஒரு மதச்சார்பற்ற நாடு மற்றும் சமூக நல்லிணக்கம்",
            subtopicQuery = "",
            order = 24
        ),
        SubTopic(
            topicId = "LD9kN15DmskXZ4bEGGax",
            name = "Prominent personalities – Arts, Science, Literature & Philosophy",
            nameTamil = "கலை, அறிவியல், இலக்கியம் மற்றும் தத்துவம் துறைகளின் மு",
            subtopicQuery = "",
            order = 25
        ),



        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Constitution of India",
            nameTamil = "இந்திய அரசியலமைப்பு",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Preamble to the Constitution",
            nameTamil = "அரசியலமைப்பின் முகவுரை",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Salient features of the Constitution",
            nameTamil = "அரசியலமைப்பின் முக்கிய கூறுகள்",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Union, State and Union Territory",
            nameTamil = "ஒன்றியம், மாநிலம் மற்றும் யூனியன் பிரதேசங்கள்",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Citizenship",
            nameTamil = "குடியுரிமை",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Fundamental Rights",
            nameTamil = "அடிப்படை உரிமைகள்",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Fundamental Duties",
            nameTamil = "அடிப்படைக் கடமைகள்",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Directive Principles of State Policy",
            nameTamil = "அரசின் நெறிமுறைக் கோட்பாடுகள்",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Union Executive",
            nameTamil = "ஒன்றிய நிர்வாகம்",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Union Legislature",
            nameTamil = "மாநில நிர்வாகம்",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "State Executive",
            nameTamil = "மாநில நிர்வாகம்",
            subtopicQuery = "",
            order = 10
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "State Legislature",
            nameTamil = "மாநில சட்டமன்றம்",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Local Governments and Panchayat Raj",
            nameTamil = "உள்ளாட்சி அமைப்புகள் மற்றும் பஞ்சாயத்து ராஜ்",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Spirit of Federalism – Centre–State Relations",
            nameTamil = "கூட்டாட்சியின் அடிப்படைத் தன்மைகள் – மத்திய மாநில உறவுகள்",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Election",
            nameTamil = "தேர்தல்",
            subtopicQuery = "",
            order = 14
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Judiciary in India",
            nameTamil = "இந்திய நீதி அமைப்புகள்",
            subtopicQuery = "",
            order = 15
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Rule of Law",
            nameTamil = "சட்டத்தின் ஆட்சி",
            subtopicQuery = "",
            order = 16
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Corruption in public life and Anti-corruption measures",
            nameTamil = "பொதுவாழ்வில் ஊழல் மற்றும் ஊழல் தடுப்பு நடவடிக்கைகள்",
            subtopicQuery = "",
            order = 17
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Lokpal and Lok Ayukta",
            nameTamil = "லோக்பால் மற்றும் லோக் ஆயுக்தா",
            subtopicQuery = "",
            order = 18
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Right to Information",
            nameTamil = "தகவல் உரிமை",
            subtopicQuery = "",
            order = 19
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Empowerment of Women",
            nameTamil = "பெண்களுக்கு அதிகாரமளித்தல்",
            subtopicQuery = "",
            order = 20
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Consumer Protection Forums",
            nameTamil = "நுகர்வோர் பாதுகாப்பு அமைப்புகள்",
            subtopicQuery = "",
            order = 21
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Human Rights Charter",
            nameTamil = "மனித உரிமைகள் சாசனம்",
            subtopicQuery = "",
            order = 22
        ),
        SubTopic(
            topicId = "XRikUwW96zZOxdF2x53A",
            name = "Political parties and political system in India",
            nameTamil = "இந்தியாவில் அரசியல் கட்சிகளும் மற்றும் ஆட்சியல் முறைகளும்",
            subtopicQuery = "",
            order = 23
        ),




        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Nature of Indian Economy",
            nameTamil = "இந்தியப் பொருளாதாரத்தின் இயல்புகள்",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Five-Year Plan Models, Planning Commission and NITI Aayog",
            nameTamil = "ஐந்தாண்டு திட்ட மாதிரிகள் – ஒரு மதிப்பீடு; திட்டக்குழு மற்றும் நிதி ஆயோக்",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Sources of Revenue",
            nameTamil = "வருவாய் ஆதாரங்கள்",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Reserve Bank of India",
            nameTamil = "இந்திய ரிசர்வ் வங்கி",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Fiscal Policy and Monetary Policy",
            nameTamil = "நிதி கொள்கை மற்றும் பணவியல் கொள்கை",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Finance Commission and Resource Sharing between Union and States",
            nameTamil = "நிதி ஆணையம் – மத்திய மாநில அரசுகளுக்கிடையேயான நிதிப் பகிர்வு",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Goods and Services Tax (GST)",
            nameTamil = "சரக்கு மற்றும் சேவை வரி",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Structure of Indian Economy and Employment Generation",
            nameTamil = "இந்திய பொருளாதார அமைப்பு மற்றும் வேலைவாய்ப்பு உருவாக்கம்",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Land Reforms and Agriculture",
            nameTamil = "நிலச் சீர்திருத்தங்கள் மற்றும் வேளாண்மை",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Application of Science and Technology in Agriculture",
            nameTamil = "வேளாண்மையில் அறிவியல் தொழில்நுட்பத்தின் பயன்பாடு",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Industrial Growth",
            nameTamil = "தொழில் வளர்ச்சி",
            subtopicQuery = "",
            order = 10
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Rural Welfare Oriented Programs",
            nameTamil = "ஊரக நலன்சார் திட்டங்கள்",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Social Problems – Population, Education, Health, Employment and Poverty",
            nameTamil = "சமூகப் பிரச்சனைகள் – மக்கள் தொகை, கல்வி, நலவாழ்வு, வேலைவாய்ப்பு, வறுமை",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Human Development Indicators in Tamil Nadu – Comparative Assessment",
            nameTamil = "தமிழ்நாட்டின் மனிதவள மேம்பாட்டுக் குறியீடுகளும் தேசிய ஒப்பாய்வும்",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Impact of Social Reform Movements in Tamil Nadu",
            nameTamil = "தமிழ்நாட்டின் சமூக பொருளாதார வளர்ச்சிக்கு சமூக மறுமலர்ச்சி இயக்கங்களின் பங்களிப்பு",
            subtopicQuery = "",
            order = 14
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Political Parties and Welfare Schemes",
            nameTamil = "அரசியல் கட்சிகளும் பலதரப்பு மக்களுக்கான நலத்திட்டங்களும்",
            subtopicQuery = "",
            order = 15
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Reservation Policy and Access to Social Resources",
            nameTamil = "இட ஒதுக்கீட்டுக் கொள்கைக்கான நியாயங்களும் சமூக வளங்களைப் பெறும் வாய்ப்புகளும்",
            subtopicQuery = "",
            order = 16
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Economic Trends in Tamil Nadu",
            nameTamil = "தமிழ்நாட்டின் பொருளாதார போக்குகள்",
            subtopicQuery = "",
            order = 17
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Role of Social Welfare Schemes in Tamil Nadu",
            nameTamil = "தமிழ்நாட்டின் சமூக பொருளாதார வளர்ச்சியில் சமூகநலத் திட்டங்களின் தாக்கமும் பங்களிப்பும்",
            subtopicQuery = "",
            order = 18
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Social Justice and Social Harmony",
            nameTamil = "சமூக நீதியும் சமூக நல்லிணக்கமும் சமூகப் பொருளாதார மேம்பாட்டின் மூலாதாரங்கள்",
            subtopicQuery = "",
            order = 19
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Education and Health Systems in Tamil Nadu",
            nameTamil = "தமிழ்நாட்டின் கல்வி மற்றும் நலவாழ்வு முறைமைகள்",
            subtopicQuery = "",
            order = 20
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Geography of Tamil Nadu and its Impact on Economic Growth",
            nameTamil = "தமிழ்நாட்டின் புவியியல் கூறுகளும் பொருளாதார வளர்ச்சியில் அவற்றின் தாக்கமும்",
            subtopicQuery = "",
            order = 21
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Achievements of Tamil Nadu in Various Fields",
            nameTamil = "பல்வேறு துறைகளில் தமிழ்நாடு நிகழ்த்தியுள்ள சாதனைகள்",
            subtopicQuery = "",
            order = 22
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "e-Governance in Tamil Nadu",
            nameTamil = "தமிழ்நாட்டில் மின்னாளுகை",
            subtopicQuery = "",
            order = 23
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Public Awareness and General Administration",
            nameTamil = "பொது விழிப்புணர்வும் பொது நிர்வாகமும்",
            subtopicQuery = "",
            order = 24
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Welfare Oriented Government Schemes and Public Delivery Systems",
            nameTamil = "நலன்சார் அரசுத் திட்டங்களும் பொது விநியோக அமைப்புகளில் நிலவும் சிக்கல்களும்",
            subtopicQuery = "",
            order = 25
        ),
        SubTopic(
            topicId = "XYGY2Yxli8oAeWoZj7rm",
            name = "Current Socio-Economic Issues",
            nameTamil = "தற்போதைய சமூகப் பொருளாதார பிரச்சனைகள்",
            subtopicQuery = "",
            order = 26
        ),




        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "History of Tamil Society and Archaeological Discoveries",
            nameTamil = "தமிழ் சமுதாய வரலாறு மற்றும் அதனுடன் தொடர்புடைய தொல்லியல் கண்டுபிடிப்புகள்",
            subtopicQuery = "",
            order = 0
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Tamil Literature from Sangam Age to Contemporary Times",
            nameTamil = "சங்க காலம் முதல் இக்காலம் வரையிலான தமிழ் இலக்கிய வரலாறு",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Thirukkural",
            nameTamil = "திருக்குறள்",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Role of Tamil Nadu in the Freedom Struggle",
            nameTamil = "விடுதலைப் போராட்டத்தில் தமிழ்நாட்டின் பங்கு",
            subtopicQuery = "",
            order = 3
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Early Agitations against British Rule",
            nameTamil = "ஆங்கிலேயருக்கு எதிரான தொடக்க கால கிளர்ச்சிகள்",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Role of Women in the Freedom Struggle",
            nameTamil = "விடுதலைப் போராட்டத்தில் பெண்களின் பங்கு",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Evolution of 19th and 20th Century Socio-Political Movements in Tamil Nadu",
            nameTamil = "பத்தொன்பது மற்றும் இருபதாம் நூற்றாண்டுகளில் தமிழ்நாட்டின் சமூக அரசியல் இயக்கங்களின் பரிணாம வளர்ச்சி",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Justice Party and Growth of Rationalism",
            nameTamil = "நீதிக்கட்சி மற்றும் பகுத்தறிவு வாதத்தின் வளர்ச்சி",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Self-Respect Movement and Dravidian Movement",
            nameTamil = "சுயமரியாதை இயக்கம், திராவிட இயக்கம் மற்றும் அவற்றின் அடிப்படை கொள்கைகள்",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "f1HCnnUoxfkYxDOBv1Jg",
            name = "Contributions of Thanthai Periyar and Perarignar Anna",
            nameTamil = "தந்தை பெரியார் மற்றும் பேரறிஞர் அண்ணாவின் பங்களிப்புகள்",
            subtopicQuery = "",
            order = 9
        ),




        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Simplification",
            nameTamil = "சுருக்குதல்",
            subtopicQuery = "Simplification",
            order = 0
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Percentage",
            nameTamil = "விழுக்காடு",
            subtopicQuery = "",
            order = 1
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Higher Common Factor (HCF)",
            nameTamil = "மீப்பெரு பொதுக் காரணி (HCF)",
            subtopicQuery = "",
            order = 2
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Lowest Common Multiple (LCM)",
            nameTamil = "மீச்சிறு பொது மடங்கு (LCM)",
            subtopicQuery = "lcm",
            order = 3
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Ratio and Proportion",
            nameTamil = "விகிதம் மற்றும் விகிதாச்சாரம்",
            subtopicQuery = "",
            order = 4
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Simple Interest",
            nameTamil = "தனிவட்டி",
            subtopicQuery = "",
            order = 5
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Compound Interest",
            nameTamil = "கூட்டு வட்டி",
            subtopicQuery = "",
            order = 6
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Area",
            nameTamil = "பரப்பு",
            subtopicQuery = "",
            order = 7
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Volume",
            nameTamil = "கொள்ளளவு",
            subtopicQuery = "",
            order = 8
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Time and Work",
            nameTamil = "காலம் மற்றும் வேலை",
            subtopicQuery = "",
            order = 9
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Logical Reasoning",
            nameTamil = "தருக்கக் காரணவியல்",
            subtopicQuery = "",
            order = 10
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Puzzles",
            nameTamil = "புதிர்கள்",
            subtopicQuery = "",
            order = 11
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Dice",
            nameTamil = "பகடை",
            subtopicQuery = "",
            order = 12
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Visual Reasoning",
            nameTamil = "காட்சிக் காரணவியல்",
            subtopicQuery = "",
            order = 13
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Alpha Numeric Reasoning",
            nameTamil = "எண் எழுத்துக் காரணவியல்",
            subtopicQuery = "",
            order = 14
        ),
        SubTopic(
            topicId = "iSGrEXW1cRg7kYC32Yya",
            name = "Number Series",
            nameTamil = "எண் வரிசை",
            subtopicQuery = "",
            order = 15
        ),




    )

    //    val sampleQuestions = listOf(
//        QuizQuestion(
//            id = 1,
//            question = "Which article of the Indian Constitution abolished untouchability?",
//            questionTamil = "இந்திய அரசியலமைப்பின் எந்த பிரிவு தீண்டாமையை ஒழித்தது?",
//            options = listOf("Article 14", "Article 15", "Article 17", "Article 21"),
//            optionsTamil = listOf("பிரிவு 14", "பிரிவு 15", "பிரிவு 17", "பிரிவு 21"),
//            answer = 2, // Article 17
//            isPremium = false
//        ),
//        QuizQuestion(
//            id = 2,
//            question = "The Reserve Bank of India was established in which year?",
//            questionTamil = "இந்திய ரிசர்வ் வங்கி எந்த ஆண்டில் நிறுவப்பட்டது?",
//            options = listOf("1935", "1947", "1950", "1969"),
//            optionsTamil = listOf("1935", "1947", "1950", "1969"),
//            answer = 0, // 1935
//            isPremium = false
//        ),
//        QuizQuestion(
//            id = 3,
//            question = "Which river is known as the 'Sorrow of Bihar'?",
//            questionTamil = "'பீகாரின் துயரம்' என்று அழைக்கப்படும் ஆறு எது?",
//            options = listOf("Ganga", "Kosi", "Yamuna", "Son"),
//            optionsTamil = listOf("கங்கை", "கோசி", "யமுனை", "சோன்"),
//            answer = 1, // Kosi
//            isPremium = false
//        ),
//        QuizQuestion(
//            id = 4,
//            question = "Who was the first Speaker of the Lok Sabha?",
//            questionTamil = "லோக்சபாவின் முதல் சபாநாயகர் யார்?",
//            options = listOf(
//                "G. V. Mavalankar",
//                "Sardar Hukam Singh",
//                "N. Sanjiva Reddy",
//                "K. S. Hegde"
//            ),
//            optionsTamil = listOf(
//                "ஜி. வி. மாவலங்கார்",
//                "சர்தார் ஹுகம் சிங்",
//                "என். சஞ்சீவ ரெட்டி",
//                "கே. எஸ். ஹெக்டே"
//            ),
//            answer = 0, // G. V. Mavalankar
//            isPremium = false
//        ),
//        QuizQuestion(
//            id = 5,
//            question = "The 'Green Revolution' in India is most closely associated with which crop?",
//            questionTamil = "இந்தியாவில் 'பசுமைப் புரட்சி' எந்த பயிருடன் மிகவும் தொடர்புடையது?",
//            options = listOf("Cotton", "Sugarcane", "Wheat", "Tea"),
//            optionsTamil = listOf("பருத்தி", "கரும்பு", "கோதுமை", "தேயிலை"),
//            answer = 2, // Wheat
//            isPremium = false
//        ),QuizQuestion(
//            id = 1,
//            question = "Which article of the Indian Constitution abolished untouchability?",
//            options = listOf("Article 14", "Article 15", "Article 17", "Article 21"),
//            answer = 2 // Article 17
//        ),
//        QuizQuestion(
//            id = 2,
//            question = "The Reserve Bank of India was established in which year?",
//            options = listOf("1935", "1947", "1950", "1969"),
//            answer = 0 // 1935
//        ),
//        QuizQuestion(
//            id = 3,
//            question = "Which river is known as the 'Sorrow of Bihar'?",
//            options = listOf("Ganga", "Kosi", "Yamuna", "Son"),
//            answer = 1 // Kosi
//        ),
//        QuizQuestion(
//            id = 4,
//            question = "Who was the first Speaker of the Lok Sabha?",
//            options = listOf(
//                "G. V. Mavalankar",
//                "Sardar Hukam Singh",
//                "N. Sanjiva Reddy",
//                "K. S. Hegde"
//            ),
//            answer = 0 // G. V. Mavalankar
//        ),
//        QuizQuestion(
//            id = 5,
//            question = "The 'Green Revolution' in India is most closely associated with which crop?",
//            options = listOf("Cotton", "Sugarcane", "Wheat", "Tea"),
//            answer = 2 // Wheat
//        ),QuizQuestion(
//            id = 1,
//            question = "Which article of the Indian Constitution abolished untouchability?",
//            options = listOf("Article 14", "Article 15", "Article 17", "Article 21"),
//            answer = 2 // Article 17
//        ),
//        QuizQuestion(
//            id = 2,
//            question = "The Reserve Bank of India was established in which year?",
//            options = listOf("1935", "1947", "1950", "1969"),
//            answer = 0 // 1935
//        ),
//        QuizQuestion(
//            id = 3,
//            question = "Which river is known as the 'Sorrow of Bihar'?",
//            options = listOf("Ganga", "Kosi", "Yamuna", "Son"),
//            answer = 1 // Kosi
//        ),
//        QuizQuestion(
//            id = 4,
//            question = "Who was the first Speaker of the Lok Sabha?",
//            options = listOf(
//                "G. V. Mavalankar",
//                "Sardar Hukam Singh",
//                "N. Sanjiva Reddy",
//                "K. S. Hegde"
//            ),
//            answer = 0 // G. V. Mavalankar
//        ),
//        QuizQuestion(
//            id = 5,
//            question = "The 'Green Revolution' in India is most closely associated with which crop?",
//            options = listOf("Cotton", "Sugarcane", "Wheat", "Tea"),
//            answer = 2 // Wheat
//        ),QuizQuestion(
//            id = 1,
//            question = "Which article of the Indian Constitution abolished untouchability?",
//            options = listOf("Article 14", "Article 15", "Article 17", "Article 21"),
//            answer = 2 // Article 17
//        ),
//        QuizQuestion(
//            id = 2,
//            question = "The Reserve Bank of India was established in which year?",
//            options = listOf("1935", "1947", "1950", "1969"),
//            answer = 0 // 1935
//        ),
//        QuizQuestion(
//            id = 3,
//            question = "Which river is known as the 'Sorrow of Bihar'?",
//            options = listOf("Ganga", "Kosi", "Yamuna", "Son"),
//            answer = 1 // Kosi
//        ),
//        QuizQuestion(
//            id = 4,
//            question = "Who was the first Speaker of the Lok Sabha?",
//            options = listOf(
//                "G. V. Mavalankar",
//                "Sardar Hukam Singh",
//                "N. Sanjiva Reddy",
//                "K. S. Hegde"
//            ),
//            answer = 0 // G. V. Mavalankar
//        ),
//        QuizQuestion(
//            id = 5,
//            question = "The 'Green Revolution' in India is most closely associated with which crop?",
//            options = listOf("Cotton", "Sugarcane", "Wheat", "Tea"),
//            answer = 2 // Wheat
//        ),
//    )
}