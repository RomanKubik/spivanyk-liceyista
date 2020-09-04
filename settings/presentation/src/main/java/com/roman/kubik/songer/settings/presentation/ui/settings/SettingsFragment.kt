package com.roman.kubik.songer.settings.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        viewModel.preferences.observe(viewLifecycleOwner, {
            showChords.isChecked = it.showChords
            preferredInstrument.setSettingsValue(resources.getStringArray(R.array.instruments)[it.selectedInstrument.ordinal])
            preferredTheme.setSettingsValue(resources.getStringArray(R.array.themes)[it.uiMode.ordinal])
        })
    }

    private fun setupUi() {
        setupToolbar(songDetailsToolbar)
        showChords.setOnCheckedChangeListener { _, isChecked ->
            viewModel.showChords(isChecked)
        }
        preferredInstrument.setOnClickListener {
            showSelectInstrumentDialog()
        }
        preferredTheme.setOnClickListener {
            showSelectUiModeDialog()
        }
        selectedDataSources.setOnClickListener {
            showSelectDataSourcesDialog()
        }
        factoryReset.setOnClickListener {
            showFactoryResetDialog()
        }
    }

    private fun showSelectInstrumentDialog() {
        MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialog_preferred_instrument)
                .setSingleChoiceItems(resources.getStringArray(R.array.instruments),
                        viewModel.preferences.value?.selectedInstrument?.ordinal
                                ?: 0) { dialog, which ->
                    viewModel.selectInstrument(Instrument.values()[which])
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                }
                .show()
    }

    private fun showSelectUiModeDialog() {
        MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialog_preferred_theme)
                .setSingleChoiceItems(resources.getStringArray(R.array.themes), viewModel.preferences.value?.uiMode?.ordinal
                        ?: 0) { dialog, which ->
                    viewModel.changeUiMode(UiMode.values()[which])
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                }
                .show()
    }

    private fun showSelectDataSourcesDialog() {
        val selectedDataSources = viewModel.getSelectedDataSources()
        MaterialAlertDialogBuilder(requireContext())
                .setTitle("Select remote data sources")
                .setMultiChoiceItems(viewModel.allDataSources.map { it.sourceName }.toTypedArray(), selectedDataSources)
                { _, which, isChecked ->
                    selectedDataSources[which] = isChecked
                }
                .setPositiveButton("Accept") { _, _ -> viewModel.selectDataSources(selectedDataSources) }
                .setNegativeButton(R.string.cancel) { _, _ -> }
                .show()

    }

    private fun showFactoryResetDialog() {
        MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialog_factory_reset)
                .setMessage(R.string.dialog_factory_reset_text)
                .setPositiveButton(R.string.dialog_reset) { _, _ ->
                    viewModel.factoryReset()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                }
                .show()
    }
}