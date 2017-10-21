package com.rviannaoliveira.vreddit.detail

import com.rviannaoliveira.vreddit.data.DataManagerFactory
import com.rviannaoliveira.vreddit.data.DataManagerInterface
import timber.log.Timber

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailPresenterImpl(private var detailView: DetailInterface.DetailView?,
                          private var dataManagerInterface: DataManagerInterface? = DataManagerFactory.getDefaultInstance()) : DetailInterface.DetailPresenter {

    override fun onViewCreated(id: String, connectedToInternet: Boolean) {
        detailView?.showProgressBar()
        loadComments(id, connectedToInternet)
    }

    override fun onDestroy() {
        detailView = null
        dataManagerInterface = null
    }

    private fun loadComments(id: String, connectedToInternet: Boolean) {
        val observableComments = if (connectedToInternet) {
            dataManagerInterface?.getAllCommentsNew(id)
        } else {
            dataManagerInterface?.getAllCommentsNewLocal(id)
        }

        observableComments?.let {
            observableComments.subscribe({ comments ->
                detailView?.loadComments(comments)
                detailView?.hideProgressBar()
            }, { error ->
                detailView?.error()
                detailView?.hideProgressBar()
                Timber.w(error)
            })
        }
    }
}
