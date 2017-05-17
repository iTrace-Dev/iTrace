#include "mainwindow.h"
#include "tobii_research.h"
#include "tobii_research_eyetracker.h"
#include <windows.h>
#include <QApplication>
#include <cstdint>

#define FINDALLTRACKERS(name) TobiiResearchStatus TOBII_RESEARCH_CALL name(TobiiResearchEyeTrackers ** eyetrackers)
typedef FINDALLTRACKERS(FindAllTrackers);
FINDALLTRACKERS(FindAllTrackersStub)
{
    return (TobiiResearchStatus)1;
}
FindAllTrackers *findAllTrackers = FindAllTrackersStub;


#define GETSYSTEMTIMESTAMP(name) TobiiResearchStatus TOBII_RESEARCH_CALL name(int64_t * timestamp)
typedef GETSYSTEMTIMESTAMP(GetSystemTimestamp);
GETSYSTEMTIMESTAMP(GetSystemTimeStub)
{
    return (TobiiResearchStatus)0;
}
GetSystemTimestamp *getSystemTime = GetSystemTimeStub;

int main(int argc, char *argv[])
{
    TobiiResearchEyeTrackers* eyetrackers = NULL;
    //tobii_research_find_all_eyetrackers(&eyetrackers);
    HMODULE tobiiLibrary = LoadLibraryA("C:\\TobiiPro.SDK.C_Binding.Windows_1.0.4.97\\64\\lib\\tobii_research.dll");
    findAllTrackers = (FindAllTrackers *)GetProcAddress(tobiiLibrary, "tobii_research_find_all_eyetrackers");
    TobiiResearchStatus result = findAllTrackers(&eyetrackers);
    qDebug() << (result == TOBII_RESEARCH_STATUS_OK);
    getSystemTime = (GetSystemTimestamp *)GetProcAddress(tobiiLibrary,"tobii_research_get_system_time_stamp");
    int64_t time = 0;
    result = getSystemTime(&time);
    qDebug() << (result == TOBII_RESEARCH_STATUS_OK);

    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    return a.exec();
}
