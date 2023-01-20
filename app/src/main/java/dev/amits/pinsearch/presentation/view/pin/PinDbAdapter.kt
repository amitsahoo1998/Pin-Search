package dev.amits.pinsearch.presentation.view.pin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.databinding.CityListItemBinding

class PinDbAdapter : RecyclerView.Adapter<PinDbAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<PinCodeResponse.Hits.Hit.Source>(){
        override fun areItemsTheSame(
            oldItem: PinCodeResponse.Hits.Hit.Source,
            newItem: PinCodeResponse.Hits.Hit.Source
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PinCodeResponse.Hits.Hit.Source,
            newItem: PinCodeResponse.Hits.Hit.Source
        ): Boolean {
            return oldItem.State.hashCode() == newItem.State.hashCode()
        }


    }
    val differ = AsyncListDiffer(this , callback)

    inner class MyViewHolder(private val itemBinding: CityListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(source: PinCodeResponse.Hits.Hit.Source) {
            itemBinding.tvStreet.text = source.Street
            itemBinding.tvCity.text = source.City + " (${source.Pincode})"
            itemBinding.tvState.text = "State :-${source.State}"
            itemBinding.tvStateCode.text = source.state_code
            itemBinding.textView3.text = source.country
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