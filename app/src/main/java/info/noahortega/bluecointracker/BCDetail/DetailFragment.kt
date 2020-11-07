package info.noahortega.bluecointracker.BCDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import info.noahortega.bluecointracker.SharedViewModel
import info.noahortega.bluecointracker.database.BlueCoin
import info.noahortega.bluecointracker.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val model: SharedViewModel by activityViewModels()

    lateinit var coin: BlueCoin

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coin = model.getSelectedCoin()!!

        activity?.title = requireContext().resources.getString(
            context?.resources!!.getIdentifier(coin.shortTitle, "string", context?.packageName)
        )

        binding.topMedia.setImageResource(
            resources.getIdentifier(coin.imageAddress, "drawable", requireContext().packageName)
        )

        binding.descriptionText.text = resources.getString(
            resources.getIdentifier(coin.description,"string", requireContext().packageName)
        )

        binding.checkBox.isChecked = coin.checked

        binding.checkBox.setOnClickListener { checkClicked() }

        binding.creditFullText.text = resources.getString(
            resources.getIdentifier(model.getSelectedCoinLevel()!!.guideAddress,"string", requireContext().packageName)
        )
    }

    private fun checkClicked() {
        //in database
        model.updateCoin(coin.coinId,binding.checkBox.isChecked, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}