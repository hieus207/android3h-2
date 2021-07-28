package com.example.mototest.Model;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mototest.Api.TestQS;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "motortest";
    private static final String TABLE_TESTS = "tests";
    private static final String KEY_IDTEST = "idtest";
    private static final String KEY_LISTQUESTION = "listquestion";
    private static final String KEY_TIME= "time";
    private static final String TABLE_QUESTIONS = "questions";
    private static final String KEY_IDQUESTION = "idquestion";
    private static final String KEY_QUESTIONFORM = "questionform";
    private static final String KEY_CONTENT= "content";
    private static final String KEY_IMAGE= "image";
    private static final String KEY_DA1 = "da1";
    private static final String KEY_DA2 = "da2";
    private static final String KEY_DA3 = "da3";
    private static final String KEY_DA4= "da4";
    private static final String KEY_DADUNG= "dadung";
    private static final String TABLE_USERS = "users";
    private static final String KEY_IDUSER = "iduser";
    private static final String KEY_USERNAME= "username";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_ACTIVE= "active";
    private static final String KEY_RECOVER = "recover";
    private static final String KEY_ACCESS_TOKEN= "access_token";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS+ "("
                + KEY_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD  + " TEXT," + KEY_NAME +"TEXT," +KEY_ACTIVE + "INTEGER,"+ KEY_RECOVER +"TEXT,"+KEY_ACCESS_TOKEN+"TEXT"+" )";
        db.execSQL(CREATE_USER_TABLE);
//        final String INSERT_USER="INSERT INTO "+TABLE_USERS+" VALUES( 1,'hieulaptop','123','hieuque',123,'bavsv','aaa')";
//        db.execSQL(INSERT_USER);
//        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTIONS+ "("
//                + KEY_IDQUESTION + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ KEY_QUESTIONFORM + " TEXT," + KEY_CONTENT + " TEXT,"
//                + KEY_IMAGE + " TEXT," + KEY_DA1 +"TEXT," + KEY_DA2 + "TEXT,"+ KEY_DA3 +"TEXT,"+ KEY_DA4 +"TEXT,"+ KEY_DADUNG +"TEXT"+" )";
        String CREATE_QUESTION_TABLE =   "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                KEY_IDQUESTION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_QUESTIONFORM + " TEXT, "
                + KEY_CONTENT + " TEXT NOT NULL, "
                // Here ------------^
                + KEY_IMAGE + " TEXT, "
                // Here ---------^
                + KEY_DA1 + " TEXT, "
                // Here ------------^
                + KEY_DA2 + " TEXT, "
                + KEY_DA3 + " TEXT, "
                + KEY_DA4 + " TEXT, "
                // Here -----------------^
                + KEY_DADUNG + " TEXT NOT NULL"
                // Here ----------------^
                + ")";
        Log.e("gi sql",CREATE_QUESTION_TABLE);

        db.execSQL(CREATE_QUESTION_TABLE);
//        final String INSERT_QUESTION="INSERT INTO "+TABLE_QUESTIONS+" VALUES( null,'kiến thức luật','“Làn đường” là gì?','hhhhhhhh','Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.','Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.', 'Là đường cho xe ô tô chạy, dừng, đỗ an toàn.','Null','2')";
//        db.execSQL(INSERT_QUESTION);

        String CREATE_TESTS_TABLE = "CREATE TABLE " + TABLE_TESTS + "("
                + KEY_IDTEST + " INTEGER ," + KEY_LISTQUESTION + " INTEGER ,"
                + KEY_TIME  + " TEXT," +"PRIMARY KEY (" +KEY_IDTEST+","+KEY_LISTQUESTION+") ," +"  FOREIGN KEY(listquestion) REFERENCES questions(idquestion))";
        db.execSQL(CREATE_TESTS_TABLE);
//        final String INSERT_TEST="INSERT INTO "+TABLE_TESTS+" VALUES( null,'1','00:00')";
//        db.execSQL(INSERT_TEST);
//        final String INSERT_TEST1="INSERT INTO "+TABLE_TESTS+" VALUES( null,'1','00:00')";
//        db.execSQL(INSERT_TEST1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TESTS);
        onCreate(db);
    }

    public void addTest(TestQS test) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDTEST,test.getIdtest());
        values.put(KEY_LISTQUESTION, test.getListquestion());
        values.put(KEY_TIME , test.getTime());
        db.insert(TABLE_TESTS, null, values);
        db.close();
    }
    public int getTestCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TESTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
    public void deleteTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_IDQUESTION + " = ?",
                new String[] { String.valueOf(test.getIdtest()) });
        db.close();
    }
    public int updateTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDTEST, test.getIdtest());
        values.put(KEY_LISTQUESTION, String.valueOf(test.getListquestion()));
        values.put(KEY_TIME, test.getTime());
        return db.update(TABLE_TESTS, values, KEY_IDTEST + " = ?",
                new String[] { String.valueOf(test.getIdtest()) });
    }


    public Test getTest(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TESTS, new String[] { KEY_IDTEST,
                        KEY_LISTQUESTION, KEY_TIME }, KEY_IDTEST + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Test test = new Test(Integer.parseInt(cursor.getString(0)),
                null, cursor.getString(2));
        Log.e("adadad",test.toString());
        return test;

    }

    public ArrayList<Test> getAllTest() {
        ArrayList<Test> testList = new ArrayList<Test>();
        String selectQuery = "SELECT DISTINCT "+KEY_IDTEST+" FROM " + TABLE_TESTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Test test = new Test();
                test.setIdtest(Integer.parseInt(cursor.getString(0)));
//               test.setListquestion(cursor.getString(1)); cái này là arraylist t chưa lấy đc
//                test.setTime(cursor.getString(2));
                testList.add(test);
                Log.e("adadad",test.toString());
            } while (cursor.moveToNext());
        }

        return testList;
    }

    public ArrayList<Question> getQSinTest(int idTest) {
        ArrayList<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS +" WHERE "+KEY_IDQUESTION+" in (SELECT "+KEY_LISTQUESTION+" FROM "+TABLE_TESTS+" WHERE "+KEY_IDTEST+" = "+idTest+" )";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        
//        Log.e("abc",Tong);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setIdquestion(Integer.parseInt(cursor.getString(0)));
                question.setQuestionform(cursor.getString(1));
                question.setContent(cursor.getString(2));
                question.setImage(cursor.getString(3));
                question.setDa1(cursor.getString(4));
                question.setDa2(cursor.getString(5));
                question.setDa3(cursor.getString(6));
                question.setDa4(cursor.getString(7));
                question.setDadung(cursor.getString(8));
                quesList.add(question);
//                Log.e("adadad",test.toString());
            } while (cursor.moveToNext());
        }

        return quesList;
    }
    public void addQuestion(Question question) {
        Log.e("id:",Integer.toString(question.getIdquestion()));
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDQUESTION, question.getIdquestion());
        values.put(KEY_QUESTIONFORM, question.getQuestionform());
        values.put(KEY_CONTENT, question.getContent());
        values.put(KEY_IMAGE, question.getImage());
        values.put(KEY_DA1, question.getDa1());
        values.put(KEY_DA2, question.getDa2());
        values.put(KEY_DA3, question.getDa3());
        values.put(KEY_DA4, question.getDa4());
        values.put(KEY_DADUNG, question.getDadung());
        db.insert(TABLE_QUESTIONS, null, values);
        db.close();
    }
    public int updateQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDQUESTION, question.getIdquestion());
        values.put(KEY_QUESTIONFORM, question.getQuestionform());
        values.put(KEY_CONTENT, question.getContent());
        values.put(KEY_IMAGE, question.getImage());
        values.put(KEY_DA1, question.getDa1());
        values.put(KEY_DA2, question.getDa2());
        values.put(KEY_DA3, question.getDa3());
        values.put(KEY_DA4, question.getDa4());
        values.put(KEY_DADUNG, question.getDadung());
        return db.update(TABLE_QUESTIONS, values, KEY_IDQUESTION + " = ?",
                new String[] { String.valueOf(question.getIdquestion()) });
    }
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_IDQUESTION + " = ?",
                new String[] { String.valueOf(question.getIdquestion()) });
        db.close();
    }
    public int getQuestionCount() {
        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
    public Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_IDQUESTION,
                        KEY_QUESTIONFORM, KEY_CONTENT, KEY_IMAGE, KEY_DA1,KEY_DA2,KEY_DA3,KEY_DA4,KEY_DADUNG }, KEY_IDQUESTION + "=?",

                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Question question= new Question(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
                cursor.getString(8));
        return question;
    }
    public ArrayList<Object> getAllQuestion() {
        ArrayList<Object> questionList = new ArrayList<Object>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setIdquestion(Integer.parseInt(cursor.getString(0)));
                question.setQuestionform(cursor.getString(1));
                question.setContent(cursor.getString(2));
                question.setImage(cursor.getString(3));
                question.setDa1(cursor.getString(4));
                question.setDa2(cursor.getString(5));
                question.setDa3(cursor.getString(6));
                question.setDa4(cursor.getString(7));
                question.setDadung(cursor.getString(8));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        return questionList;
    }

}
