package dev.amits.pinsearch.presentation.view.pin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.databinding.CityListItemBinding

class PinAdapter : RecyclerView.Adapter<PinAdapter.MyViewHolder>() {

    private val callback = object :DiffUtil.ItemCallback<PinCodeResponse.Hits.Hit>(){
        override fun areItemsTheSame(
            oldItem: PinCodeResponse.Hits.Hit,
            newItem: PinCodeResponse.Hits.Hit
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: PinCodeResponse.Hits.Hit,
            newItem: PinCodeResponse.Hits.Hit
        ): Boolean {
            return oldItem._source.id == newItem._source.id
        }

    }
    val differ = AsyncListDiffer(this , callback)

    inner class MyViewHolder(private val itemBinding: CityListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(hit: PinCodeResponse.Hits.Hit) {
            itemBinding.tvStreet.text = hit._source.Street
            itemBinding.tvCity.text = hit._source.City +" (${hit._source.Pincode})"
            itemBinding.tvState.text = "State :-${hit._source.State}"
            itemBinding.tvStateCode.text = hit._source.state_code
            itemBinding.textView3.text = hit._source.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CityListItemBinding = CityListItemBinding.inflate(layoutInflater , parent ,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}