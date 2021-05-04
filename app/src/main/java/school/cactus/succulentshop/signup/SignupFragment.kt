package school.cactus.succulentshop.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import school.cactus.succulentshop.R
import school.cactus.succulentshop.signup.validation.EmailValidator
import school.cactus.succulentshop.signup.validation.UsernameValidator
import school.cactus.succulentshop.databinding.FragmentSignupBinding
import school.cactus.succulentshop.signup.validation.PasswordValidator

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    private val usernameValidator = UsernameValidator()

    private val emailValidator = EmailValidator()

    private val passwordValidator = PasswordValidator()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            signUpButton.setOnClickListener {
                if (passwordInputLayout.isValid() and usernameInputLayout.isValid() and
                    emailInputLayout.isValid()) {
                    findNavController().navigate(R.id.loginSuccessful)
                }
            }
        }

        requireActivity().title = getString(R.string.log_in)
    }

    private fun TextInputLayout.isValid(): Boolean {
        val errorMessage = validator().validate(editText!!.text.toString())
        error = errorMessage?.resolveAsString()
        isErrorEnabled = errorMessage != null
        return errorMessage == null
    }

    private fun Int.resolveAsString() = getString(this)

    private fun TextInputLayout.validator() = when (this) {
        binding.usernameInputLayout -> usernameValidator
        binding.emailInputLayout -> emailValidator
        binding.passwordInputLayout -> passwordValidator
        else -> throw IllegalArgumentException("Cannot find any validator for the given TextInputLayout")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
