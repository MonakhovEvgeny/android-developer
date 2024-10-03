package com.example.lesson4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Найти элементы интерфейса
        val nameInput = findViewById<EditText>(R.id.name_input)
        val phoneInput = findViewById<EditText>(R.id.phone_input)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.gender_radio_group)
        val notificationSwitch = findViewById<Switch>(R.id.notification_switch)
        val emailCheckbox = findViewById<CheckBox>(R.id.email_checkbox)
        val smsCheckbox = findViewById<CheckBox>(R.id.sms_checkbox)
        val progressBar = findViewById<ProgressBar>(R.id.score_progress)
        val progressText = findViewById<TextView>(R.id.score_text)
        val saveButton = findViewById<Button>(R.id.save_button)

        // Установка начального состояния чекбоксов на основе состояния Switch
        updateCheckboxState(notificationSwitch, emailCheckbox, smsCheckbox)

        // Очистка текста при нажатии на кнопку удаления
        nameInput.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (nameInput.right - nameInput.compoundDrawables[2].bounds.width())) {
                    nameInput.text.clear() // Очищаем поле
                    validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox) // Проверяем кнопку
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        // Установка случайного значения прогресса
        val randomProgress = Random.nextInt(101) // Случайное число от 0 до 100
        progressBar.progress = randomProgress
        progressText.text = "$randomProgress баллов" // Дублирование прогресса в TextView

        // Логика блокировки чекбоксов при выключенном Switch
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateCheckboxState(notificationSwitch, emailCheckbox, smsCheckbox)
            validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
        }

        // Логика работы чекбоксов — если они изменяются, проверяем условия для кнопки
        emailCheckbox.setOnCheckedChangeListener { _, _ ->
            validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
        }
        smsCheckbox.setOnCheckedChangeListener { _, _ ->
            validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
        }

        // Логика работы кнопки "Сохранить"
        saveButton.setOnClickListener {
            if (isFormValid(nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)) {
                // Если все условия выполнены, показываем уведомление
                Toast.makeText(this, "Информация сохранена", Toast.LENGTH_LONG).show()
            }
        }

        // Слежение за изменениями в текстовых полях
        nameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        phoneInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Проверка состояния кнопки и чекбоксов при загрузке
        validateSaveButton(saveButton, nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
    }

    // Проверка корректности формы
    private fun isFormValid(
        nameInput: EditText,
        phoneInput: EditText,
        genderRadioGroup: RadioGroup,
        notificationSwitch: Switch,
        emailCheckbox: CheckBox,
        smsCheckbox: CheckBox
    ): Boolean {
        // Условие 1: Поле "Имя" должно быть не пустым и содержать до 40 символов
        val isNameValid = nameInput.text.isNotBlank() && nameInput.text.length <= 40

        // Условие 2: Поле "Телефон" не должно быть пустым
        val isPhoneValid = phoneInput.text.isNotBlank()

        // Условие 3: Пол должен быть выбран
        val isGenderSelected = genderRadioGroup.checkedRadioButtonId != -1

        // Условие 4: Если переключатель активен, хотя бы один вид уведомлений должен быть выбран
        val isNotificationValid = if (notificationSwitch.isChecked) {
            emailCheckbox.isChecked || smsCheckbox.isChecked
        } else {
            true // Если Switch выключен, это условие не применяется
        }

        return isNameValid && isPhoneValid && isGenderSelected && isNotificationValid
    }

    // Валидация кнопки "Сохранить"
    private fun validateSaveButton(
        saveButton: Button,
        nameInput: EditText,
        phoneInput: EditText,
        genderRadioGroup: RadioGroup,
        notificationSwitch: Switch,
        emailCheckbox: CheckBox,
        smsCheckbox: CheckBox
    ) {
        saveButton.isEnabled = isFormValid(nameInput, phoneInput, genderRadioGroup, notificationSwitch, emailCheckbox, smsCheckbox)
    }

    // Обновление состояния чекбоксов в зависимости от состояния Switch
    private fun updateCheckboxState(
        notificationSwitch: Switch,
        emailCheckbox: CheckBox,
        smsCheckbox: CheckBox
    ) {
        emailCheckbox.isEnabled = notificationSwitch.isChecked
        smsCheckbox.isEnabled = notificationSwitch.isChecked
    }
}
