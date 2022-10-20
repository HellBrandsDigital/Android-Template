package com.hellbrandsdigital.android_template.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hellbrandsdigital.android_template.MAIN_PART_FRAGMENT_TAG
import com.hellbrandsdigital.androidtemplate.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hiddenValue.text = viewModel.hiddenValue.value.toString()
        binding.textMainPart.text = viewModel.mainText.value

        setListener()
        setObserver()
    }

    private fun setObserver() {
        val mainTextObserver = Observer<String> { newText ->
            binding.textMainPart.text = newText
        }

        val hiddenValueObserver = Observer<Int> { newValue ->
            binding.hiddenValue.text = newValue.toString()
        }

        viewModel.mainText.observe(viewLifecycleOwner, mainTextObserver)
        viewModel.hiddenValue.observe(viewLifecycleOwner, hiddenValueObserver)
    }

    private fun setListener() {
        binding.textMainPart.setOnClickListener {
            Log.d(MAIN_PART_FRAGMENT_TAG, "Clicked on main text")
            viewModel.incrementHiddenValue()
        }
    }
}
