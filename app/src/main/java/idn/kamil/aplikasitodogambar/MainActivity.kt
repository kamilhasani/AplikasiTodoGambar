package idn.kamil.aplikasitodogambar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import idn.kamil.aplikasitodogambar.databinding.ActivityMainBinding
import idn.kamil.aplikasitodogambar.model.ModelData
import idn.kamil.aplikasitodogambar.recyclerview.adapter.ItemDataAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // buat variabel adapter untuk recyclerview
    private lateinit var adapterMain: ItemDataAdapter

    //buat variabel database referance dari firebase
    private lateinit var databaseUser: DatabaseReference

    private lateinit var valueEventListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //membuat binding di oncreate
        val inflater : LayoutInflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(binding.root)

        //isi lateinit var adapterMain
        adapterMain = ItemDataAdapter()

        //isi lateinit var databaseUser dr firebase
        databaseUser = FirebaseDatabase.getInstance().reference.child("user")

        binding.extendedFab.setOnClickListener {
            val intent = Intent(this, AddDataActivity::class.java)
            startActivity(intent)
        }

        // setting RecyclerView
        binding.rvMain.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterMain
            setHasFixedSize(true)
        }

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.childrenCount > 0) {
                    val daftarUser = arrayListOf<ModelData>()
                    for(dataUser in snapshot.children) {
                        val data = dataUser.getValue(ModelData::class.java) as ModelData
                        daftarUser.add(data)
                    }
                    //memasukan database yg tlh didapatkan ke dalam adapter recycleview
                    adapterMain.addData(daftarUser)
                    //beri tahu adapter recycleview jika ada perubahan data
                    adapterMain.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }

        //tambahkan valueeventlistener untk mengecek data yg ada di firebase
        databaseUser.addValueEventListener(valueEventListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        // ini jangan dihapus.. setiap kali kita menambahkan eventlistener
        // maka perlu dihapus dengan cara removeEventListener
        // jika penambahan terjadi di oncreate
        // maka hapusnya itu ada di onDestroy seperti kode di bawah ini
        databaseUser.removeEventListener(valueEventListener)
    }
}