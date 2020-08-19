package com.roman.kubik.songer.settings.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.UiMode
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.settings.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        viewModel.preferences.observe(viewLifecycleOwner, Observer {
            showChords.isChecked = it.showChords
            preferredInstrument.setSettingsValue(it.selectedInstrument.name)
            preferredTheme.setSettingsValue(it.uiMode.name)
        })
    }

    private fun setupUi() {
        showChords.setOnClickListener {
            viewModel.showChords(!showChords.isChecked)
        }
        preferredInstrument.setOnClickListener {
            showSelectInstrumentDialog()
        }
        preferredTheme.setOnClickListener {
            showSelectUiModeDialog()
        }
        factoryReset.setOnClickListener {
            showFactoryResetDialog()
        }
    }

    private fun showSelectInstrumentDialog() {
        AlertDialog.Builder(requireContext())
                .setTitle("Preferred instrument")
                .setSingleChoiceItems(arrayOf("Guitar", "Ukulele"), viewModel.preferences.value?.selectedInstrument?.ordinal
                        ?: 0) { dialog, which ->
                    viewModel.selectInstrument(Instrument.values()[which])
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
                .show()
    }

    private fun showSelectUiModeDialog() {
        AlertDialog.Builder(requireContext())
                .setTitle("Preferred theme")
                .setSingleChoiceItems(UiMode.values().map { it.name }.toTypedArray(), viewModel.preferences.value?.uiMode?.ordinal
                        ?: 0) { dialog, which ->
                    viewModel.changeUiMode(UiMode.values()[which])
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
                .show()
    }

    private fun showFactoryResetDialog() {
        AlertDialog.Builder(requireContext())
                .setTitle("Factory reset")
                .setMessage("Factory reset message")
                .setPositiveButton("Reset") { _, _ ->
                    viewModel.factoryReset()
                }
                .setNegativeButton("Discard") { _, _ ->
                }
                .show()
    }
}