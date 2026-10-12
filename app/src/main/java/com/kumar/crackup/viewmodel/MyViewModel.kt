package com.kumar.crackup.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.crackup.auth.CreateAccountFormInput
import com.kumar.crackup.auth.SignInFormInput
import com.kumar.crackup.dailyChallenge.DailyChallengeDAO
import com.kumar.crackup.dailyChallenge.DailyChallengeUiState
import com.kumar.crackup.model.Exam
import com.kumar.crackup.model.NewQuestion
import com.kumar.crackup.model.QueryModel
import com.kumar.crackup.model.SubTopic
import com.kumar.crackup.model.TamilUnit
import com.kumar.crackup.model.Topic
import com.kumar.crackup.model.TopicType
import com.kumar.crackup.dailyChallenge.toDailyChallengeForRoom
import com.kumar.crackup.progress.StreakCalculator
import com.kumar.crackup.states.NavStateClass
import com.kumar.crackup.test.AttemptModel
import com.kumar.crackup.util.FirebaseUtil
import com.kumar.crackup.util.LoadingManager
import com.kumar.crackup.util.NetworkConnectivityObserver
import com.kumar.crackup.util.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val loadingManager: LoadingManager,
    private val dailyChallengeDAO: DailyChallengeDAO
): ViewModel() {

    private val _initialNav = Channel<NavStateClass>(Channel.BUFFERED)
    val initialNav = _initialNav.receiveAsFlow()

    val isEnglish = MutableStateFlow(true)
    val isEng = isEnglish.asStateFlow()

    val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topic = _topics.asStateFlow()

    private val _selectedExam = MutableStateFlow<Exam?>(null)
    val selectedExam: StateFlow<Exam?> = _selectedExam.asStateFlow()

    private val connectivityObserver = NetworkConnectivityObserver(context)

    val networkStatus: StateFlow<NetworkStatus> = connectivityObserver.networkStatus
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NetworkStatus.Available
        )

    val currentUser =  FirebaseUtil.getCurrentUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
    val exams: StateFlow<List<Exam>> = _exams.asStateFlow()

    private val _tamilUnits = MutableStateFlow<List<TamilUnit>>(emptyList())
    val units: StateFlow<List<TamilUnit>> = _tamilUnits.asStateFlow()

    private val _selectedUnit = MutableStateFlow<TamilUnit?>(null)
    val selectedUnit: StateFlow<TamilUnit?> = _selectedUnit.asStateFlow()

    val currentTopic = MutableStateFlow<Topic?>(null)


    private val _subTopics = MutableStateFlow<List<SubTopic>>(emptyList())
    val subTopics: StateFlow<List<SubTopic>> = _subTopics.asStateFlow()

    private val _query = MutableStateFlow<QueryModel>(QueryModel())
    val query: StateFlow<QueryModel> = _query.asStateFlow()

    private val _questions = MutableStateFlow<List<NewQuestion>>(emptyList())
    val questions: StateFlow<List<NewQuestion>> = _questions.asStateFlow()

    val _splashShown = MutableStateFlow<Boolean>(false)
    val splashShown = _splashShown.asStateFlow()

    val _dailyChallenge = MutableStateFlow<DailyChallengeUiState>(DailyChallengeUiState())
    val dailyChallenge = _dailyChallenge.asStateFlow()

    val topicProgress = FirebaseUtil.getTopicProgress().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val globalProgress = FirebaseUtil.getGlobalProgress().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val _globalTotalQuestions = MutableStateFlow<Int>(0)
    val globalTotal = _globalTotalQuestions.asStateFlow()

    val monthlyActivity = FirebaseUtil.getMonthlyActivity()
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            _initialNav.send(NavStateClass.ShowSplash)
        }
        viewModelScope.launch {
            _topics.value = FirebaseUtil.getTopics()
            _globalTotalQuestions.value = calculateGlobalQuestions(_topics.value)
        }
    }

    fun getDailyChallenge() {
        viewModelScope.launch {
            val todayDate = StreakCalculator.getTodayDate()
            dailyChallengeDAO.getDailyChallenge().collect { data ->
                when {
                    data == null -> {
                        Log.d("ROOMD", ": room data null ${data}")
                        val questions = FirebaseUtil.getDailyChallenge()
                        _dailyChallenge.value = _dailyChallenge.value.copy(questions = questions)
                        dailyChallengeDAO.insetQuestions(_dailyChallenge.value.toDailyChallengeForRoom())
                    }
                    (!data.date.equals(todayDate)) -> {
                        Log.d("ROOMD", ": room data yesterday: ${data}")
                        dailyChallengeDAO.deleteAllQuestions()
                        val questions = FirebaseUtil.getDailyChallenge()
                        _dailyChallenge.value = _dailyChallenge.value.copy(questions = questions)
                        dailyChallengeDAO.updateQuestions(_dailyChallenge.value.toDailyChallengeForRoom())

                    }

                    (data.date.equals(todayDate)) -> {
                        Log.d("ROOMD", ": room data today ${data}")
                        _dailyChallenge.value = DailyChallengeUiState(
                            questions = data.questions,
                            attempted = data.answeredQuestions,
                            currentQuestionIndex = data.currentQuestionIndex,
                            answers = data.answers,
                            isCompleted = data.isCompleted
                        )
                    }
                }
            }
        }
    }
    fun updateDailyChallenge() {
        viewModelScope.launch {
//            val questions = FirebaseUtil.getDailyChallenge()
//            _dailyChallenge.value = _dailyChallenge.value.copy(questions = questions)
        }
    }

    fun updateRoom(dailyChallengeUiState: DailyChallengeUiState) {
        viewModelScope.launch {
            Log.d("ROOMD", "updateRoom: ${dailyChallengeUiState.toDailyChallengeForRoom()}")
            dailyChallengeDAO.updateQuestions(dailyChallengeUiState.toDailyChallengeForRoom())
        }
    }

    fun calculateGlobalQuestions(topics: List<Topic>): Int {
        return topics.sumOf { it.totalQuestions }
    }
    fun updateSplashShown(shown: Boolean) {
        _splashShown.value = shown
    }

    fun updateNavState(newState: NavStateClass)  {
        viewModelScope.launch {
            _initialNav.send(newState)
        }
    }

    fun addAttempt(attemptModel: AttemptModel, parentName: String, totalQuestions: Int, globalQuestionsCount: Int) {
        viewModelScope.launch {
            Log.d("ERROR", "QuizResultScreen: ${attemptModel}")
            FirebaseUtil.addAttempt(attemptModel, parentName, totalQuestions, globalQuestionsCount)
        }
    }

//    fun getTopicProgress() {
//        viewModelScope.launch {
//            _topicProgress.value = FirebaseUtil.getTopicProgress()
//        }
//    }
//    fun getGlobalProgress() {
//        viewModelScope.launch {
//            _globalProgress.value = FirebaseUtil.getGlobalProgress()
//        }
//    }

//    fun getDailyActivity() {
//        viewModelScope.launch {
//            _dailyActivity.value = FirebaseUtil.getDailyActivity()
////            Log.d(TAG, "getDailyActivity: ${}")
//        }
//    }
    fun topicSelected(newTopic: Topic, onNavToExam: () -> Unit, onNavToSubTopic: () -> Unit, onNavTamilUnits: () -> Unit) {

        viewModelScope.launch {
            _query.value = QueryModel()
            _query.value = _query.value.copy(mainTopic = newTopic.topicQuery, parentName = newTopic.name, totalQuestions = newTopic.totalQuestions)
            when(newTopic.type) {
                 TopicType.EXAM_YEAR -> {
                     _exams.value = FirebaseUtil.getExams()
                     onNavToExam()
                 }

                TopicType.SUB_TOPIC -> {
                     _subTopics.value = emptyList()
                    Log.d("LUCKY", "topicSelected: ${newTopic}")
                    _subTopics.value = FirebaseUtil.getSubTopics(newTopic.id)
//                    _subTopics.value = FirebaseUtil.getSubTopics("d890df7f-a0eb-4b5a-b8ed-4a30b321d8db")
                    onNavToSubTopic()
                }

                TopicType.UNIT_SUB_TOPIC -> {
                    _tamilUnits.value = FirebaseUtil.getTamil()
                    Log.d("LUCKY", "topicSelected: ${units.value}")
                    onNavTamilUnits()
                }
            }
        }
        currentTopic.value = newTopic
    }

    fun onExamSelected(exam: Exam) {
        val convertedExam = romanNotation(exam.name)
        _query.value = _query.value.copy(subTopic = convertedExam)
        _selectedExam.value = exam
    }

    private fun romanNotation(name: String): String {
        return when(name){
            "Group |" -> "Group 1"
            "Group ||" -> "Group 2"
            else -> "Group"
        }
    }

    fun onUnitSelected(unit: TamilUnit) {
        _query.value = _query.value.copy(subTopic = unit.unitName)
        _selectedUnit.value = unit
    }

    fun onUnitSubTopicSelected(selectedTamilSubTopic: String, onSuccess: () -> Unit) {
        _query.value = _query.value.copy(subCollection = selectedTamilSubTopic)
        isEnglish.value = false
        onSuccess()
    }

    fun onYearSelected(selectedYear: String, onSuccess: () -> Unit) {
        _query.value = _query.value.copy(subCollection = selectedYear)
        onSuccess()
    }

    fun onSubTopicSelected(selectedSubTopic: String, onSuccess: () -> Unit) {
        _query.value = _query.value.copy(subTopic = _query.value.mainTopic, subCollection = selectedSubTopic)
        onSuccess()
    }

    fun getQuestions(queryModel: QueryModel, onSuccess: () -> Unit) {
        viewModelScope.launch {
            loadingManager.show()
            val questions = FirebaseUtil.getQuestions(queryModel)
            _questions.value = questions
            loadingManager.hide()
            onSuccess()
        }
    }

    fun changeLanguage(isEngSelected: Boolean) {
        isEnglish.value = isEngSelected
    }

//    fun getExams(onSuccess: (List<Exam>) -> Unit, onError: (String) -> Unit) {
//        viewModelScope.launch {
//            try {
//                FirebaseUtil.getExams(
//                    onSuccess = {
//                        onSuccess(it)
//                    },
//                    onError = {
//                        onError()
//                    }
//                )
//            } catch (e: Exception) {
//
//            }
//        }
//
//    }



    fun signin(input: SignInFormInput, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            loadingManager.show()
            try {
                val result = FirebaseUtil.loginUser(input.userEmail, input.password)
                onSuccess()
            } catch (e: Exception) {
                onError()
            } finally {
                delay(150)
                loadingManager.hide()
            }

        }
    }

    fun logout(onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            loadingManager.show()
            try {
                val result = FirebaseUtil.signOut()
                delay(300)
                onSuccess()
            } catch (e: Exception) {
                onError()
            } finally {
                delay(150)
                loadingManager.hide()
            }
        }
    }

    fun signup(input: CreateAccountFormInput, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            loadingManager.show()
            try {
                val result = FirebaseUtil.signUpUser(input)
                FirebaseUtil.addUser(
                    result, input, onSuccess = {
                        onSuccess()
                    },
                    onError = {
                       onError()
                    })
            } catch (e: Exception) {
                onError()
            } finally {
                loadingManager.hide()
            }

        }
    }
}