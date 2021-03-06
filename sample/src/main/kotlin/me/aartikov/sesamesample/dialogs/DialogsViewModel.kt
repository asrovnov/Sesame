package me.aartikov.sesamesample.dialogs

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.aartikov.sesame.dialog.DialogControl
import me.aartikov.sesame.property.command
import me.aartikov.sesamesample.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DialogsViewModel @Inject constructor() : BaseViewModel() {

    val showMessage = command<String>()

    val dialog = DialogControl<String, Unit>()
    val dialogForResult = DialogControl<String, DialogResult>()

    fun onShowDialogButtonClicked() {
        dialog.show("Some message")
    }

    fun onShowForResultButtonClicked() {
        viewModelScope.launch {
            val result = dialogForResult.showForResult("Some message for result") ?: DialogResult.CANCEL

            if (result == DialogResult.OK) {
                showMessage("OK")
            } else {
                showMessage("Cancel")
            }
        }
    }
}