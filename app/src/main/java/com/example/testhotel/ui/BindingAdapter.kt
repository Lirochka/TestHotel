package com.example.testhotel.ui


import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.testhotel.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener

@BindingAdapter("errorInput")
fun bindErrorInput(
    textInputLayout: TextInputLayout,
    isError: Boolean,
    showNameError: Boolean,
) {
    val errorColor = if (isError && showNameError) {
        textInputLayout.context.getColor(R.color.error)
    } else {
        null
    }
    textInputLayout.boxBackgroundColor = errorColor ?: Color.LTGRAY
}
@BindingAdapter("app:showError")
fun setShowError(textInputLayout: TextInputLayout, showError: LiveData<Boolean>) {
    showError.observeForever { shouldShowError ->
        textInputLayout.boxBackgroundColor = if (shouldShowError) {
            textInputLayout.context.getColor(R.color.error)
        } else {
            ContextCompat.getColor(textInputLayout.context, R.color.gray_light)
        }
    }
}


@BindingAdapter("errorPhone")
fun bindPhoneError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}
@BindingAdapter("phoneMask")
fun bindPhoneMask(textInputEditText: TextInputEditText, mask: String) {
    val listener = MaskedTextChangedListener(
        mask,
        true,
        textInputEditText,
        null,
        null
    )

    textInputEditText.addTextChangedListener(listener)
    textInputEditText.onFocusChangeListener = listener
}
@BindingAdapter("errorEmail")
fun bindEmailError(textInputLayout: TextInputLayout, error: LiveData<String>?) {
    error?.let {
        textInputLayout.error = it.value
        val backgroundColor = if (it.value != null) {
            ContextCompat.getColor(textInputLayout.context, R.color.error)
        } else {
            ContextCompat.getColor(textInputLayout.context, R.color.noError)
        }
        textInputLayout.boxBackgroundColor = backgroundColor
    }
}

@BindingAdapter("onFocusChange")
fun bindOnFocusChange(
    textInputEditText: TextInputEditText,
    onFocusChangeListener: View.OnFocusChangeListener?,
) {
    textInputEditText.onFocusChangeListener = onFocusChangeListener
}

