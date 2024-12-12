package com.example.firebasesamplejonathan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebasesamplejonathan.databinding.FragmentGithubLoginBinding
import com.example.firebasesamplejonathan.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [GithubLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GithubLoginFragment : Fragment() {
   /* // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null*/

    val provider = OAuthProvider.newBuilder("github.com")

    private var _binding: FragmentGithubLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 /*       arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGithubLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing the auth
        auth = Firebase.auth
        //setUpGithubAuth()

        binding.githubLoginButton.setOnClickListener{
            Toast.makeText(context, "Look at you you done click da button", Toast.LENGTH_LONG).show()
            setUpGithubAuth()
        }
    }

    private fun setUpGithubAuth() {
        val pendingResultTask = auth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener { task ->
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                    val user = auth.currentUser
                    Toast.makeText(context, "Good Login by ${user?.email}", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { task ->
                    // Handle failure.
                    Toast.makeText(context, "Bad Login: ${task.message}", Toast.LENGTH_LONG)
                        .show()
                }
        } else {
            // There's no pending result so you need to start the sign-in flow.
            signInWithProvider(provider)
        }
    }

    fun signInWithProvider(provider: OAuthProvider.Builder) {
        // [START auth_oidc_provider_signin]
        auth.startActivityForSignInWithProvider(requireActivity(), provider.build())
            .addOnSuccessListener {
                // User is signed in.
                // IdP data available in
                // authResult.getAdditionalUserInfo().getProfile().
                // The OAuth access token can also be retrieved:
                // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                // The OAuth secret can be retrieved by calling:
                // ((OAuthCredential)authResult.getCredential()).getSecret().
            }
            .addOnFailureListener {
                // Handle failure.
            }
        // [END auth_oidc_provider_signin]
    }

/*    private fun firebaseAuthWithGithub(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Log.d("User", user?.email.toString())
            } else {
                Log.d("User", task.exception?.message.toString())
            }
        }
    }*/




    /*
        companion object {
            *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GithubLoginFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GithubLoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}