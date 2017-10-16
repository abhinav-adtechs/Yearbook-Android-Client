package in.vit.yearbook.Presenter.Main;


public interface MainActivityCommunicationInterface {

    void notifyStatus(int status) ;

    void updateDownloadingStatus(int year, int progress) ;
}
