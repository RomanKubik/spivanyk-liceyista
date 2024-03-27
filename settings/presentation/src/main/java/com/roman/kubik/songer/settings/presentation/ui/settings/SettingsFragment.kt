package com.roman.kubik.songer.settings.presentation.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.roman.kubik.settings.domain.preference.Instrument
import com.roman.kubik.settings.domain.preference.UiMode
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.settings.presentation.R
import com.roman.kubik.songer.settings.presentation.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        viewModel.preferences.observe(viewLifecycleOwner) {
            binding.apply {
                showChords.isChecked = it.showChords
                preferredInstrument.setSettingsValue(resources.getStringArray(R.array.instruments)[it.selectedInstrument.ordinal])
                preferredTheme.setSettingsValue(resources.getStringArray(R.array.themes)[it.uiMode.ordinal])
                helpDeveloper.isChecked = it.showAds
            }
        }
    }

    private fun setupUi() {
        binding.apply {
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
            helpDeveloper.setOnCheckedChangeListener { _, isChecked ->
                viewModel.allowAds(isChecked)
            }
            derussification.setOnClickListener {
                showDerussificationDialog()
            }
            factoryReset.setOnClickListener {
                showFactoryResetDialog()
            }
        }
    }

    private fun showSelectInstrumentDialog() {
        AlertDialog.Builder(requireContext())
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
        AlertDialog.Builder(requireContext())
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
        AlertDialog.Builder(requireContext())
                .setTitle(R.string.dialog_remote_data_source)
                .setMultiChoiceItems(viewModel.allDataSources.map { it.sourceName }.toTypedArray(), selectedDataSources)
                { _, which, isChecked ->
                    selectedDataSources[which] = isChecked
                }
                .setPositiveButton(R.string.accept) { _, _ -> viewModel.selectDataSources(selectedDataSources) }
                .setNegativeButton(R.string.cancel) { _, _ -> }
                .show()

    }

    private fun showDerussificationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_derussification)
            .setMessage(R.string.dialog_derussification_text)
            .setPositiveButton(R.string.dialog_derussification_accept) { _, _ ->
                viewModel.derussify()
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
            }
            .show()
    }

    private fun showFactoryResetDialog() {
        AlertDialog.Builder(requireContext())
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