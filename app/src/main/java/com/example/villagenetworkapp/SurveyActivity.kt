package com.example.villagenetworkapp

class SurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        // ставим цифры 1‑10 в две полосы
        numberize(findViewById(R.id.lineKindness))
        numberize(findViewById(R.id.lineEfficiency))

        // если нужна логика выбора — подписываем радиокнопки
        attachCheckListener(findViewById(R.id.lineKindness)) { score ->
            Toast.makeText(this, "Kindness = $score", Toast.LENGTH_SHORT).show()
        }
        attachCheckListener(findViewById(R.id.lineEfficiency)) { score ->
            Toast.makeText(this, "Efficiency = $score", Toast.LENGTH_SHORT).show()
        }

        // кнопка «назад»
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }
    }

    /** Проставить подписи 1‑10 под кружками */
    private fun numberize(line: LinearLayout) {
        for (i in 0 until line.childCount) {
            val holder = line.getChildAt(i) as LinearLayout
            holder.findViewById<TextView>(R.id.label).text = (i + 1).toString()
        }
    }

    /** Сделать так, чтобы выбирался только один кружок и возвращать оценку */
    private fun attachCheckListener(
        line: LinearLayout,
        onSelect: (score: Int) -> Unit
    ) {
        for (i in 0 until line.childCount) {
            val holder = line.getChildAt(i) as LinearLayout
            val rb = holder.findViewById<RadioButton>(R.id.radio)

            rb.setOnClickListener {
                // снимаем выбор со всех остальных
                for (j in 0 until line.childCount) {
                    val h = line.getChildAt(j) as LinearLayout
                    h.findViewById<RadioButton>(R.id.radio).isChecked = false
                }
                rb.isChecked = true
                onSelect(i + 1)          // позиции 0‑9 → оценка 1‑10
            }
        }
    }
}
