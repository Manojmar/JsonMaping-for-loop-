package com.example.jsondemo

data class curriculumData(
    val additional_language: List<AdditionalLanguage>,
    val batch_timings: List<BatchTiming>,
    val board_exam: List<BoardExam>,
    val exam_center: List<ExamCenter>,
    val grades: List<Grade>,
    val header: String, // Success
    val school_type: List<SchoolType>,
    val second_language: List<SecondLanguage>,
    val status_code: Int, // 200
    val third_language: List<ThirdLanguage>
) {
    data class AdditionalLanguage(
        val comment: String,
        val grades: ArrayList<String>,
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val field_input_name: String // Arabic
        )
    }

    data class BatchTiming(
        val grade_id: String, // 111001
        val input_data: ArrayList<InputData>,
        val school_type_id: String // 0
    ) {
        data class InputData(
            val comment: String, // Note : Indian Standard Time (+5.30 GMT)
            val field_input_name: String, // 09.30 am - 10.30 am
            val timing_id: String // 9
        )
    }

    data class BoardExam(
        val comment: String,
        val grade_id: String, // 111012
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val field_input_name: String, // NIOS as a Private candidate
            val field_input_token: String
        )
    }

    data class ExamCenter(
        val grade_id: String, // 111012
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val field_input_name: String // Nearest NIOS Examination / Affiliated Center In India
        )
    }

    data class Grade(
        val curriculum: String, // Indian
        val input_data: ArrayList<InputData>,
        val token: String // 970001
    ) {
        data class InputData(
            val enroll_for: String, // no
            val field_input_name: String, // Nursery
            val field_input_token: String, // 111001
            val grade_id: String, // 111001
            val grade_type_id: String, // 1
            val homeschooled: String, // no
            val schooling_type: String // no
        )
    }

    data class SchoolType(
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val comment: String,
            val field_input_name: String, // Day School
            val grade_type_id: String, // 3
            val school_type_id: String // 1
        )
    }

    data class SecondLanguage(
        val comment: String,
        val grades: ArrayList<String>,
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val field_input_name: String // Arabic
        )
    }

    data class ThirdLanguage(
        val comment: String,
        val grades: ArrayList<String>,
        val input_data: ArrayList<InputData>
    ) {
        data class InputData(
            val field_input_name: String // Arabic
        )
    }
}