package com.edsonlimadev.shopapp.presenter.finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentFinishBinding


class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFinishBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackHome.setOnClickListener {
            findNavController().navigate(R.id.action_global_nav_home)
        }

    }
}