package com.arthurfp.cookaholic.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.arthurfp.cookaholic.R
import com.arthurfp.cookaholic.databinding.SplashScreenLayoutBinding
import com.arthurfp.cookaholic.util.Constants.Companion.SPLASH_SCREEN_DURATION


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: SplashScreenLayoutBinding

    private lateinit var fadeInAnimation: Animation
    private lateinit var fadeInSloganFirstAnimation: Animation
    private lateinit var fadeInSloganSecondAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(window) {

            // Set Full-Screen for Splash Screen
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow_animation)
        fadeInSloganFirstAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)
        fadeInSloganSecondAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)

        fadeInAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.splashSloganFirst.visibility = View.VISIBLE
                binding.splashSloganFirst.animation = fadeInSloganFirstAnimation
            }
        })

        fadeInSloganFirstAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.splashSloganSecond.visibility = View.VISIBLE
                binding.splashSloganSecond.animation = fadeInSloganSecondAnimation
            }
        })


        binding.splashLogo.animation = fadeInAnimation

        Handler(Looper.getMainLooper()).postDelayed({
            //val intent = Intent(this, LoginActivity::class.java)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.fade_out_animation);
            finish()
        }, SPLASH_SCREEN_DURATION)

    }
}