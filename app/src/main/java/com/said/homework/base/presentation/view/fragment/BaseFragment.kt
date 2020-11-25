package com.said.homework.base.presentation.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Ahmed Sa'eed on 20/11/2020.
 */
abstract class BaseFragment : Fragment() {
    lateinit var activity: Activity
    lateinit var baseFragmentViewBinding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutResourceId(), container, false)
        baseFragmentViewBinding = getViewBinding(view)!!
        return view
    }

    @LayoutRes
    protected abstract fun getLayoutResourceId(): Int

    protected abstract fun getViewBinding(view: View?): ViewBinding?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // In Kotlin context can not match to activity
        activity = try {
            context as Activity
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must be instance of Activity"
            )
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Code here
            try {
                this.activity = activity
            } catch (e: ClassCastException) {
                throw ClassCastException(
                    activity.toString()
                            + " must be instance of Activity"
                )
            }
        } else {
            this.activity = activity
        }
    }

    /**
     * Gets a component for dependency injection by its type.
     */
//    protected open fun <C> getComponent(componentType: Class<C>): C {
//        return componentType.cast((getActivity() as HasComponent<C>?).getComponent())
//    }
}