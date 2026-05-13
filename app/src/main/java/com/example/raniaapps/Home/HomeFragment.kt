package com.example.raniaapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raniaapps.AuthActivity
import com.example.raniaapps.Home.pertemuan_2.SecondActivity
import com.example.raniaapps.Home.pertemuan_3.ThirdActivity
import com.example.raniaapps.Home.pertemuan_4.FourthActivity
import com.example.raniaapps.Home.pertemuan_5.FifthActivity
import com.example.raniaapps.Home.pertemuan_7.SeventhActivity
import com.example.raniaapps.Home.pertemuan_9.NinthActivity
import com.example.raniaapps.R
import com.example.raniaapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
    val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

    binding.btnToFourth.setOnClickListener {
        val intent = Intent(requireContext(), FourthActivity::class.java)

        intent.putExtra("name", "Politeknik Caltex Riau")
        intent.putExtra("from", "Rumbai")
        intent.putExtra("age", 25)

        startActivity(intent)
    }

    binding.btnToSec.setOnClickListener {
        val intent = Intent(requireContext(), SecondActivity::class.java)

        intent.putExtra("name", "Politeknik Caltex Riau")
        intent.putExtra("from", "Rumbai")
        intent.putExtra("age", 25)

        startActivity(intent)
    }

    binding.btnToThird.setOnClickListener {
        val intent = Intent(requireContext(), ThirdActivity::class.java)

        intent.putExtra("name", "Politeknik Caltex Riau")
        intent.putExtra("from", "Rumbai")
        intent.putExtra("age", 25)

        startActivity(intent)
    }

    binding.btnToFifth.setOnClickListener {
        val intent = Intent(requireContext(), FifthActivity::class.java)

        intent.putExtra("name", "Politeknik Caltex Riau")
        intent.putExtra("from", "Rumbai")
        intent.putExtra("age", 25)

        startActivity(intent)
    }

    binding.btnToSeventh.setOnClickListener {
        val intent = Intent(requireContext(), SeventhActivity::class.java)

        intent.putExtra("name", "Politeknik Caltex Riau")
        intent.putExtra("from", "Rumbai")
        intent.putExtra("age", 25)

        startActivity(intent)
    }
        binding.btnToNinth.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }
    binding.btnLogout.setOnClickListener {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin melanjutkan?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()

                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(requireContext(), AuthActivity::class.java)
                startActivity(intent)
                Log.e("Info Dialog","Anda memilih Ya!")
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Log.e("Info Dialog","Anda memilih Tidak!")
            }
            .show()
    }

}

}