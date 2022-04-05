package com.kotlin.testapplication

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kotlin.testapplication.databinding.FragmentFirstBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    //    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            val hour: Int = 10;
//            val minute: Int = 4;
//            var today = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).withNano(0)
//
//            var test = "TESTING today" + "\n"
//            test += today.toString() + "\n"
//
//
//            today = today.plusDays(1)
//            test += today.toString() + "\n"
//            binding.textviewFirst.text = test
//        }
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val hour = 10
            val minute = 4
            var today = calcEndTime(hour, minute, 1648706400000)

            var test = "TESTING today" + "\n"
            test += today.toString() + "\n"


            today = today.plusDays(1)
            test += today.toString() + "\n"
            binding.textviewFirst.text = test
        }
    }

    fun calcEndTime(hour: Int, minute: Int, initinmilliseconds: Long): LocalDateTime {
        var endtime = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).withNano(0)
        val now = LocalDateTime.now().withSecond(0).withNano(0)
        val inittime = Instant.ofEpochMilli(initinmilliseconds).atZone(ZoneOffset.UTC).toLocalDateTime()
        val days = inittime.dayOfMonth - now.dayOfMonth

        if (days == 1 || days < 0) {
            endtime = endtime.plusDays(1)
            Log.d("ZXCV", "if ${endtime}")
        }

        if (endtime.isBefore(now)) {
            endtime = endtime.plusDays(1)
            Log.d("ZXCV2", "if ${endtime}")
        }
        return endtime//.toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    fun firstTest(): LocalDateTime {
        val hour: Int = 10;
        val minute: Int = 4;
        var today = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).withNano(0)

        var test = "TESTING today" + "\n"
        test += today.toString() + "\n"


        today = today.plusDays(1)
        test += today.toString() + "\n"
        return today
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}