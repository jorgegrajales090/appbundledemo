package com.time.someplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var manager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = SplitInstallManagerFactory.create(this)
        manager.registerListener(listener)

        val button1 = this.findViewById<Button>(R.id.button1)

        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, "com.time.dynamicfeature.InstantMainActivity")
                startActivity(intent)

            }
        })

        val button2 = this.findViewById<Button>(R.id.button2)

        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val moduleName = "Modulo"
                val request = SplitInstallRequest.newBuilder()
                    .addModule(moduleName)
                    .build()

                manager.startInstall(request)

            }
        })

        val button3 = this.findViewById<Button>(R.id.button3)

        button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                OpenMainActivity()

            }
        })

        val button4 = this.findViewById<Button>(R.id.button4)

        button4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val installedModules = manager.installedModules.toList()
                manager.deferredUninstall(installedModules).addOnSuccessListener {
                    ShowToast("Uninstalling $installedModules")
                }.addOnFailureListener {
                    ShowToast("Failed installation of $installedModules")
                }

            }
        })
    }

    private val listener = SplitInstallStateUpdatedListener { state ->

        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
               ShowToast("Descargando")
            }
            SplitInstallSessionStatus.INSTALLED -> {
                OpenMainActivity()
            }

            SplitInstallSessionStatus.INSTALLING -> {
                ShowToast("Instalando")
            }
            SplitInstallSessionStatus.FAILED -> {
                ShowToast("Fallido")
            }
        }

    }

    private fun ShowToast(message: String)
    {
        val t = Toast.makeText(this, message, Toast.LENGTH_LONG)
        t.show()
    }

    private fun OpenMainActivity()
    {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, "com.time.modulo.MainActivityModulo")
        startActivity(intent)
    }

}
