package com.dmi.dao

import androidx.room.*
import com.dmi.entity.*

@Dao
interface QuizzDao {
@Transaction
@Query("SELECT * FROM Question")
suspend fun getQuestionsWithChoices(): List<QuestionChoice>

@Transaction
@Query("SELECT * FROM Question")
suspend fun getQuestionsWithAnswers(): List<QuestionAnswer>

@Query("SELECT * FROM Question")
suspend fun getQuestions(): List<Question>

@Query("SELECT * FROM Submission where submittedBy = :name AND quizId=:quizId")
suspend fun getSubmissionByName(name : String, quizId: Int): List<Submission>

@Query("SELECT * FROM Quizz where active=1")
suspend fun getActiveQuiz(): Quizz
@Query("SELECT * FROM Answer")
suspend fun getAnswers(): List<Answer>
@Query("SELECT * FROM Choice")
suspend fun getChoices(): List<Choice>
@Insert
suspend fun addQuestion(question: Question)
@Insert
suspend fun addQuestions(vararg question: Question)
@Insert
suspend fun submitAnswers(vararg submission: Submission)
@Insert
suspend fun setAnswer(answer: Answer)
@Insert
suspend fun setAnswers(vararg answer: Answer)
@Insert
suspend fun addChoice(choice: Choice)
@Insert
suspend fun addChoices(vararg choice: Choice)
@Insert
suspend fun addQuiz(quiz: Quizz)
@Update
suspend fun updateQuiz(quiz: Quizz)
@Query("DELETE FROM submission where quizId=:quizid and submittedBy=:submitBy")
suspend fun deleteByName(quizid:Int, submitBy:String)
}