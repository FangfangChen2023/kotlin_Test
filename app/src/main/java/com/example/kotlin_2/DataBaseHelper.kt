import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.kotlin_2.model.GoalItem

val DATABASENAME = "Steptracker"
val TABLENAME = "Goals"
val COL_ID = "ID"
val COL_GOAL_NAME = "name"
val COL_GOAL_ACTIVE = "active"
val COL_GOAL_STEPS = "steps"

//Database from https://www.tutorialspoint.com/how-to-use-a-simple-sqlite-database-in-kotlin-android
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_GOAL_NAME + " VARCHAR(256)," + COL_GOAL_ACTIVE + " INTEGER," + COL_GOAL_STEPS + " INTEGER)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insert(goalItem: GoalItem) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_GOAL_NAME, goalItem.name)
        contentValues.put(COL_GOAL_ACTIVE, goalItem.active)
        contentValues.put(COL_GOAL_STEPS, goalItem.steps)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateGoal(goalItem: GoalItem){
        deleteGoal(goalItem)
        insert(goalItem)
    }

    fun deleteGoal(goalItem:GoalItem){
        val database = this.writableDatabase
        database.delete(TABLENAME, "name=?", arrayOf(goalItem.name))
    }

    @SuppressLint("Range")
    fun readData(): MutableList<GoalItem> {
        val list: MutableList<GoalItem> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val goalName = result.getString(result.getColumnIndex(COL_GOAL_NAME))
                val goalSteps = result.getString(result.getColumnIndex(COL_GOAL_STEPS)).toInt()
                val goalActive = result.getString(result.getColumnIndex(COL_GOAL_ACTIVE)).toInt()
                val goalActiveBool = goalActive == 1
                val goalItem = GoalItem(goalName, goalSteps, goalActiveBool)
                /*goalItem.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()*/
                list.add(goalItem)
            }
            while (result.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun getActiveGoal(): GoalItem {
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME Where $COL_GOAL_ACTIVE = 1"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {

                val goalName = result.getString(result.getColumnIndex(COL_GOAL_NAME))
                val goalSteps = result.getString(result.getColumnIndex(COL_GOAL_STEPS)).toInt()
                val goalActive = result.getString(result.getColumnIndex(COL_GOAL_ACTIVE)).toInt()
                val goalActiveBool = goalActive == 1
                val goalItem = GoalItem(goalName, goalSteps, goalActiveBool)
                /*goalItem.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()*/
                return(goalItem)

        }
        //Toast.makeText(context, "Failed, no result for getting active goal", Toast.LENGTH_SHORT).show()

        return(GoalItem("default", 5000, true))
    }

    fun updateGoals(goalItem: GoalItem) {
        val goals = readData()
        for (goal in goals){
            if (goalItem.name == goal.name){
                goal.active = true
                updateGoal(goal)
            } else {
                goal.active = false
                updateGoal(goal)
            }
        }

    }

    fun clearDatabase(){
        val goals = readData()
        for (goal in goals){
            deleteGoal(goal)
        }
        }
}