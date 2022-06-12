package com.example.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dictionary.Adapters.RvAdapter
import com.example.dictionary.Models.Word
import com.example.dictionary.Utils.MySharedPreference
import com.example.dictionary.databinding.FragmentUzbekBinding
import com.google.firebase.firestore.FirebaseFirestore

class UzbekFragment : Fragment() {
    lateinit var binding: FragmentUzbekBinding
    lateinit var firestore: FirebaseFirestore
    private lateinit var words:ArrayList<Word>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUzbekBinding.inflate(LayoutInflater.from(context))
        firestore = FirebaseFirestore.getInstance()
        MySharedPreference.init(context)

        if (MySharedPreference.uzbWords.isEmpty()) {
            binding.lottie.visibility = View.VISIBLE
            words = ArrayList()
            firestore.collection("word").get().addOnCompleteListener {
                if (it.isSuccessful)
                    for (document in it.result) {
                        words.add(
                            Word(
                                document.data.getValue("uzbek").toString(),
                                document.data.getValue("english").toString()
                            )
                        )
                        binding.rv.adapter =
                            RvAdapter(requireContext(), words, object : RvAdapter.ItemListener {
                                override fun onClick(position: Int) {
                                    findNavController().navigate(
                                        R.id.infoFragment,
                                        bundleOf(
                                            "desc" to words[position].descWord,
                                            "title" to words[position].titleWord
                                        )
                                    )
                                }

                            })
                        MySharedPreference.uzbWords = words
                        binding.lottie.visibility = View.INVISIBLE
                    }
            }
        }else{
            binding.rv.adapter =
            RvAdapter(requireContext(), MySharedPreference.uzbWords, object : RvAdapter.ItemListener {
                override fun onClick(position: Int) {
                    findNavController().navigate(
                        R.id.infoFragment,
                        bundleOf(
                            "desc" to MySharedPreference.uzbWords[position].descWord,
                            "title" to MySharedPreference.uzbWords[position].titleWord
                        )
                    )
                }
            })
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        list = 0
    }

}