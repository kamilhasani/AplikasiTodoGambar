package idn.kamil.aplikasitodogambar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Imam Fahrur Rofi on 04/09/2020.
 */
@Parcelize
data class ModelData(
    var profile_image : String = "",
    var profile_name : String = "",
    var profile_class : String = "",
    var profile_address : String = ""
) : Parcelable