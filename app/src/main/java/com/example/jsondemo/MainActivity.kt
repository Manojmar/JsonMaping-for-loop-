package com.example.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.jsondemo.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val grade: ArrayList<String> = ArrayList()
    var curriculamoutput: String = ""
    var gradeoutput: String = ""
    var schooloutput:String=""
    var batchoutput:String=""
    var schooltypefield: ArrayList<String> = ArrayList()
    var batchtypefield: ArrayList<String> = ArrayList()
    var curriculaminput: String = ""
    var batchtype: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//curriculum
        val curriculam: ArrayList<String> = ArrayList()
        val obj = JSONObject(loadJSONFromAsset())
        val userArray = obj.getJSONArray("grades")
        for (i in 0 until userArray.length()) {
            val userDetail = userArray.getJSONObject(i)
            curriculam.add(userDetail.getString("curriculum"))
        }


        val curriculamadapter = ArrayAdapter(this, R.layout.drop_down_link, curriculam)
        binding.dropDownCurriculum.setAdapter(curriculamadapter)

        binding.dropDownCurriculum.setOnItemClickListener { adapterView, view, i, l ->
            curriculamoutput = binding.dropDownCurriculum.text.toString()
//Grade
            grade.clear()
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                curriculaminput = userDetail.getString("curriculum")
                if (curriculaminput == curriculamoutput) {
                    val inputdate = userDetail.getJSONArray("input_data")
                    for (k in 0 until inputdate.length()) {
                        val inputdataa = inputdate.getJSONObject(k)
                        grade.add(inputdataa.getString("field_input_name"))
                    }
                }
            }
        }


        val gradeadapter = ArrayAdapter(this, R.layout.drop_down_link, grade)
        binding.dropdownGrade.setAdapter(gradeadapter)

//School Type
        binding.dropdownGrade.setOnItemClickListener { adapterView, view, i, l ->
            gradeoutput = binding.dropdownGrade.text.toString()

            val userArray = obj.getJSONArray("grades")
            val usertimeArray = obj.getJSONArray("batch_timings")


            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                val inputdate = userDetail.getJSONArray("input_data")
                for (k in 0 until inputdate.length()) {
                    val inputdataa = inputdate.getJSONObject(k)
                    val gradeinput = inputdataa.getString("field_input_name")
                    val schooltype = inputdataa.getString("schooling_type")
                    if (gradeoutput == gradeinput && schooltype == "yes") {
                        binding.dropDownSchoolContainer.visibility = View.VISIBLE
                        schooltypefield.clear()
                        val schoolgradetypeone = inputdataa.getString("grade_type_id")
                        val scltype = obj.getJSONArray("school_type")
                        for (j in 0 until scltype.length()) {
                            val userDetail = scltype.getJSONObject(j)
                            val schoooltype = userDetail.getJSONArray("input_data")
                            for (l in 0 until schoooltype.length()) {
                                val schoolinputtt = schoooltype.getJSONObject(l)
                                val schoolgradetypetwo = schoolinputtt.getString("grade_type_id")
                                if (schoolgradetypeone == schoolgradetypetwo) {
                                    schooltypefield.add(schoolinputtt.getString("field_input_name"))
                                }
                            }
                        }
                        break
                    } else {
                        binding.dropDownSchoolContainer.visibility = View.GONE
                    }
                }
                break
            }


            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                val inputdate = userDetail.getJSONArray("input_data")
                for (k in 0 until inputdate.length()) {
                    val inputdataa = inputdate.getJSONObject(k)
                    val gradeinput = inputdataa.getString("field_input_name")
                    val batchtype = inputdataa.getString("grade_id")
                    for (j in 0 until usertimeArray.length()) {
                        val usertimeDetail = usertimeArray.getJSONObject(j)
                        val inputdate = usertimeDetail.getString("grade_id")
                        if (batchtype == inputdate && gradeoutput == gradeinput ) {
                            batchtypefield.clear()
                            val batchtiming = usertimeDetail.getJSONArray("input_data")
                            for (m in 0 until batchtiming.length()) {
                                val outtiming = batchtiming.getJSONObject(m)
                                batchtypefield.add(outtiming.getString("field_input_name"))
                            }
                        }
                    }
                }

            }


            val schooltypeadapter = ArrayAdapter(this, R.layout.drop_down_link, schooltypefield)
            binding.dropDownSchool.setAdapter(schooltypeadapter)

            binding.dropDownSchool.setOnItemClickListener { adapterView, view, i, l ->
                schooloutput = binding.dropDownSchool.text.toString()



            }



            val batchtypeadapter = ArrayAdapter(this, R.layout.drop_down_link, batchtypefield)
            binding.dropDownBatch.setAdapter(batchtypeadapter)

            binding.dropDownBatch.setOnItemClickListener { adapterView, view, i, l ->
                batchoutput = binding.dropDownBatch.text.toString()



            }


        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = resources.openRawResource(R.raw.task)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}

