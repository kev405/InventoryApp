package com.univalle.inventoryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.univalle.inventoryapp.R
import com.univalle.inventoryapp.databinding.FragmentAddItemBinding
import android.text.Editable
import android.text.TextWatcher
import android.graphics.Color
import android.graphics.Typeface

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            validarCampos()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_item, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarAddItem.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.etCodigo.addTextChangedListener(textWatcher)
        binding.etNombre.addTextChangedListener(textWatcher)
        binding.etPrecio.addTextChangedListener(textWatcher)
        binding.etCantidad.addTextChangedListener(textWatcher)
    }
    private fun validarCampos() {
        val codigo = binding.etCodigo.text.toString()
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etPrecio.text.toString()
        val cantidad = binding.etCantidad.text.toString()
        val estanLlenos = codigo.isNotBlank() && nombre.isNotBlank() &&
                precio.isNotBlank() && cantidad.isNotBlank()

        binding.btnGuardar.isEnabled = estanLlenos

        if (estanLlenos) {
            binding.btnGuardar.setTextColor(Color.WHITE)
            binding.btnGuardar.setTypeface(null, Typeface.BOLD)
        } else {
            binding.btnGuardar.setTextColor(Color.LTGRAY)
            binding.btnGuardar.setTypeface(null, Typeface.NORMAL)
        }
    }
}