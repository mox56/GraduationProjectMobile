package com.android.graduationproject.view_model


//class MainActivityViewModel(private val apiConsumerImpl: APIConsumerImpl): ViewModel() {
    //lateinit var examdatalist: MutableLiveData<List<StudentIndex>?>

//init {

  //  }

   /* fun getexamdataobserver(): MutableLiveData<List<StudentIndex>?> {


    }*/

    /*suspend fun makeapicall() {
        val intent = Intent.getIntentOld("Username")
        val mynumber = intent.toString()
        val retroInstance = APIService.getRetroInstance()
        val retroService = retroInstance.create(APIConsumer::class.java)
        val call = retroService.getExamResult(number = mynumber)
        call.enqueue(object : Callback<List<StudentIndex>> {
            override fun onResponse(
                call: Call<List<StudentIndex>>,
                response: Response<List<StudentIndex>>
            ) {
                examdatalist.postValue(response.body())
            }

            override fun onFailure(call: Call<List<StudentIndex>>, t: Throwable) {
                examdatalist.postValue(null)
            }
        })
    }
}*/


