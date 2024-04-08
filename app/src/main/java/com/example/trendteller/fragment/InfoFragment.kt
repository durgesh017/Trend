package com.example.trendteller.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendteller.adapter.InfoAdapter
import com.example.trendteller.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    var steps = arrayOf(
        "1. Used system APIs to fetch news from the provided web API.",
        "2. Created a visually interactive UI for listing news articles.",
        "3. Enabled opening articles in a browser window on headline click.",
        "4. Implemented Firebase Cloud Messaging (FCM) for notifications.",
        "5. Designed custom elements for a user-friendly experience.",
        "6. Added features for improved usability.",
        "7. Ensured code quality with proper documentation and handling of exceptions.",
        "8. Implemented sorting articles by date.",
        "9. Organized the project for clarity.",
        "10. Conducted thorough testing for bug resolution.",
        "11. Followed Android development best practices.",
        "12. Implemented responsive design using relative layout.",
        "13. Ensured accessibility compliance.",
        "14. Prepared comprehensive documentation.",
        "15. Demonstrated creativity and innovation.",
    )

    var position = arrayOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater)

        rvInfoAdapter(steps,position)
        return binding.root
    }

    private fun rvInfoAdapter(steps: Array<String>, position: Array<Int>) {
        var adapter = InfoAdapter(activity,steps,position)
        var layoutManager = LinearLayoutManager(activity)
        binding.rvInfo.layoutManager = layoutManager
        binding.rvInfo.adapter = adapter

    }

}