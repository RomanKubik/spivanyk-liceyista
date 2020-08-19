package com.roman.kubik.songer.settings.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.roman.kubik.songer.core.ui.base.BaseFragment
import com.roman.kubik.songer.settings.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.simple_settings_item.view.*
import kotlinx.android.synthetic.main.switch_settings_item.view.*
import kotlinx.android.synthetic.main.switch_settings_item.view.settingsItemValue

@AndroidEntryPoint
class SettingsFragment: BaseFragment() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showChords.settingsItemSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.showChords(isChecked)
        }
        viewModel.preferences.observe(viewLifecycleOwner, Observer {
            showChords.isChecked = it.showChords
            preferredInstrument.setSettingsValue(it.selectedInstrument.name)
            preferredTheme.setSettingsValue(it.uiMode.name)
        })
    }
}