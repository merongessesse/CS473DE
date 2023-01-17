package com.dmi.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dmi.dao.QuizzDao
import com.dmi.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


@Database(entities = [
    Quizz::class,
    Question::class,
    Answer::class,
    Choice::class,
    Submission::class],
    version = 1)
abstract  class QuizDatabase : RoomDatabase(){
    private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
    abstract fun getQuizzDao() : QuizzDao
    companion object {
        @Volatile private var instance : QuizDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context, scope: CoroutineScope) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context, scope).also {
                instance = it
            }
        }

    private fun buildDatabase(context: Context,scope: CoroutineScope) = Room.databaseBuilder(
        context.applicationContext,
        QuizDatabase::class.java,
        "quizdatabase"
    ).addCallback(object :RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            scope.launch {
                instance?.let {
                        it.getQuizzDao().addQuiz(Quizz("First Quiz", "First Quizz", 1))
                        Log.d("QUIZZ -DB","lOADED QUIZZ DATA")
                        it.getQuizzDao().addQuestions(
                            Question("Android is developed by.", 0, 1),
                            Question("Android web browser is based on.", 0, 1),
                            Question("Which media format is not supported by Android.", 0, 1),
                            Question("In Which Directory XML Layout Files Are Stored.", 0, 1),
                            Question("Which Code Used By Android Is Not A Open Source.", 0, 1),
                            Question("Which Of The Following Does Not Belong To Transitions?", 0, 1),
                            Question("What Does AAPT Stands For?", 0, 1),
                            Question(" What is APK in android?",0,1 ),
                            Question("View Pager Is Used For?", 0, 1),
                            Question("What Is JNI In Android?", 0, 1),
                            Question("We can refresh the dynamic content in android by", 0, 1),
                            Question("Action Bar Can Be Associated To", 0, 1),
                            Question("Android Is Based On Which Kernal ", 0, 1),
                            Question("Adb Stands For", 0, 1),
                            Question("Android is based on Linux for which reason? ", 0, 1)
                        )
                    Log.d("QUIZZ -DB","lOADED QUESTION DATA"+it.getQuizzDao().getQuestions().first().description)
                        it.getQuizzDao().setAnswers(
                            Answer(1, "Android Inc"),
                            Answer(2, "Open-source Webkit"),
                            Answer(3, "AVI"),
                            Answer(4, "/res/layout"),
                            Answer(5, "WiFi Driver"),
                            Answer(6, "ViewSlider"),
                            Answer(7, "Android Asset Packaging Tool"),
                            Answer(8, "Android Package Kit"),
                            Answer(9, "Swiping Fragments"),
                            Answer(10, "Java Native Interface"),
                            Answer(11, "Ajax"),
                            Answer(12, "Only Activities"),
                            Answer(13, "Linux"),
                            Answer(14, "Android Debug Bridge"),
                            Answer(15, "All of the above")
                        )
                        it.getQuizzDao().addChoices(
                            Choice(1,"Google"),
                            Choice(1,"Microsoft"),
                            Choice(1,"Android Inc"),
                            Choice(2,"Chrome"),
                            Choice(2,"Open-source Webkit"),
                            Choice(2,"Safari") ,
                            Choice(3,"MP4"),
                            Choice(3,"AVI"),
                            Choice(3,"MIDI") ,
                            Choice(4,"/assets"),
                            Choice(4,"/res/layout"),
                            Choice(4,"/res/values") ,
                            Choice(5,"WiFi Driver"),
                            Choice(5,"Video Driver"),
                            Choice(5,"Bluetooth Driver"),
                            Choice(6,"ViewSwitcher"),
                            Choice(6,"ViewSwitcher"),
                            Choice(6,"ViewSlider"),
                            Choice(7,"Android Asset Processing Tool"),
                            Choice(7,"Android Asset Packaging Tool"),
                            Choice(7,"Android Asset Packaging Technique"),
                            Choice(8,"Android Package Kit"),
                            Choice(8,"Android pack"),
                            Choice(8,"Android packages"),
                            Choice(8,"None of the above"),
                            Choice(9,"Swiping Fragments"),
                            Choice(9,"Swiping Activities"),
                            Choice(9,"Paging Down List Items"),
                            Choice(10,"Java Native Interface"),
                            Choice(10,"Java Network Interface"),
                            Choice(10,"Java Interface"),
                            Choice(11,"Ajax"),
                            Choice(11,"Java"),
                            Choice(11,"Kotlin"),
                            Choice(11,"Xamarin"),
                            Choice(12,"Only Fragments"),
                            Choice(12,"Only Activities"),
                            Choice(12,"Both Activities And Fragments"),
                            Choice(13,"Redhat"),
                            Choice(13,"Linux"),
                            Choice(13,"Windows"),
                            Choice(14,"Android Debug Bridge"),
                            Choice(14,"Android Delete Bridge"),
                            Choice(14,"Android Destroy Bridge"),
                            Choice(15,"Security"),
                            Choice(15,"Portability"),
                            Choice(15,"Networking"),
                            Choice(15,"All of the above"))
                    Log.d("QUIZZ -DB","lOADED QUESTION CHOICES"+it.getQuizzDao().getChoices().size)
                    }

                }


        }
    }).build()
    }


}
