package ru.maxlord.kotlinandroidapp.activity.base

import android.app.Fragment
import android.os.Bundle
import android.view.MenuItem
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_no_action_bar.*
import ru.maxlord.kotlinandroidapp.R
import ru.maxlord.kotlinandroidapp.app.KotlinAndroidApplication


/**
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
abstract class BaseNoActionBarActivity: RxAppCompatActivity() {
    val FRAGMENT_TAG = "fragment_main"

    protected var fragment: Fragment? = null

    private lateinit var component: ActivitySubComponent

    fun getComponent(): ActivitySubComponent {
        return component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as KotlinAndroidApplication
        this.component = app.component().plus(ActivityModule(this))
        this.component.inject(this)

        setContentView(R.layout.activity_main_no_action_bar)

        initControls()

        val args = intent.extras
        if (args != null) {
            readArguments(args)
        }

        if (savedInstanceState == null) {
            fragment = loadFragment()

            if (fragment != null) {
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment, FRAGMENT_TAG).commit()
            }
        } else {
            fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected open fun initControls() {

    }

    /**
     * Инициализирует и возвращает фрагмент для отображения в активити.
     * Классы наследники должны переопределять этот метод и загружать фрагмент.

     * @return
     */
    protected abstract fun loadFragment(): Fragment

    /**
     * Используя этот метод нужно инициализироваь аргументы переданнные в активити, через
     * getIntent().getExtras();
     */
    protected open fun readArguments(args: Bundle) {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.home) {
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        // освобождаем ресурсы хелпера БД
        //		OpenHelperManager.releaseHelper();
    }
}
