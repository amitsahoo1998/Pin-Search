package dev.amits.pinsearch.presentation.view.pin

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.amits.pinsearch.R
import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.databinding.FragmentFirstBinding
import dev.amits.pinsearch.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PinFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: PinViewModel by viewModels()

    @Inject
    lateinit var adapter: PinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        observeData()

        binding.buttonSearch.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            if (isOnline(requireContext())) {
                if (binding.editTextText.text.length == 6) {
                    val filterItem =
                        PinCodeRequest.Query.Bool.Filter(
                            PinCodeRequest.Query.Bool.Filter.Match(
                                binding.editTextText.text.toString()
                            )
                        )
                    val filterList: ArrayList<PinCodeRequest.Query.Bool.Filter> = ArrayList()
                    filterList.add(filterItem)
                    val pinCodeRequest =
                        PinCodeRequest(PinCodeRequest.Query(PinCodeRequest.Query.Bool(filterList)))
                    viewModel.getPinResponse(pinCodeRequest)
                } else {
                    Snackbar.make(view, "Please enter a valid 6 digit pin ", Snackbar.LENGTH_LONG)
                }
            }else{
                Snackbar.make(view,"No Internet Connection ",Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.bottom_nav)
                    .setAction("Action",null).show()
            }
        }


    }

    private fun observeData() {
        viewModel.pinResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    result.data?.let { it->
                        if (it.hits.total.value>0) {
                            adapter.differ.submitList(it.hits.hits.toList())
                        }else{
                            adapter.differ.submitList(null)
                            Snackbar.make(requireView(), "No data found in this pin", Snackbar.LENGTH_LONG)
                        }
                    }
                }

                is Resource.Error -> {
                    binding.progressBarHorizontal.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(),result.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Timber.tag("Internet").i("NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Timber.tag("Internet").i("NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Timber.tag("Internet").i("NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}