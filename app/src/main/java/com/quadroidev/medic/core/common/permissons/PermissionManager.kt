package com.quadroidev.medic.core.common.permissons

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionManager(
    private val fragment: Fragment,
    callBoolean: (isGranted: Boolean) -> Unit
) {
    private val requestPermissionLauncher =
        fragment.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { callBoolean(it) }


    fun hasPermission(permissionType: String) = when {
        ContextCompat.checkSelfPermission(
            fragment.requireContext(),
            permissionType
        ) == PackageManager.PERMISSION_GRANTED -> true

        ActivityCompat.shouldShowRequestPermissionRationale(
            fragment.requireActivity(),
            permissionType
        ) -> {
            requestPermissionLauncher.launch(permissionType)
            false
        }

        else -> {
            // TODO Show dialog
            fragment.requireActivity().startActivity(
                Intent(
                    ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", fragment.requireContext().packageName, null)
                )
            )
            false
        }
    }

    fun getPermission(permissionType: String) {
        requestPermissionLauncher.launch(permissionType)
    }
}
