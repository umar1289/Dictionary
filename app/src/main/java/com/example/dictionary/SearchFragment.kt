package com.example.dictionary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dictionary.Adapters.RvAdapter
import com.example.dictionary.Models.Word
import com.example.dictionary.Utils.MySharedPreference
import com.example.dictionary.databinding.FragmentSearchBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var dataList: ArrayList<Word>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(context))
        dataList = ArrayList()

        dataList = if (list == 0) {
            MySharedPreference.uzbWords
        } else {
            MySharedPreference.engWords
        }
        binding.rv.adapter = RvAdapter(requireContext(),dataList, object : RvAdapter.ItemListener{
            override fun onClick(position: Int) {
                findNavController().navigate(R.id.infoFragment, bundleOf("desc" to dataList[position].descWord,"title" to dataList[position].titleWord))
            }

        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()


        binding.edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var list = ArrayList<Word>()
                if (binding.edt.text.isNotEmpty()) {
                    for (word in dataList) {
                        for (i in 0 until word.titleWord?.length!!) {
                            if (word.titleWord?.subSequence(0, i).toString()
                                    .lowercase(Locale.getDefault()) == binding.edt.text.toString()
                                    .toLowerCase()
                            ) {
                                list.add(word)
                            }
                        }
                    }
                    binding.rv.adapter = RvAdapter(requireContext(),list, object : RvAdapter.ItemListener{
                        override fun onClick(position: Int) {
                            findNavController().navigate(R.id.infoFragment, bundleOf("desc" to list[position].descWord,"title" to list[position].titleWord))
                        }
                    })
                }else{
                    binding.rv.adapter = RvAdapter(requireContext(),dataList, object : RvAdapter.ItemListener{
                        override fun onClick(position: Int) {
                            findNavController().navigate(R.id.infoFragment, bundleOf("desc" to dataList[position].descWord,"title" to dataList[position].titleWord))
                        }
                    })
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

}