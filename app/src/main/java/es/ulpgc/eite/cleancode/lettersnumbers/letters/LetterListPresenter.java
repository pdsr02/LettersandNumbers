package es.ulpgc.eite.cleancode.lettersnumbers.letters;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.lettersnumbers.app.AppMediator;
import es.ulpgc.eite.cleancode.lettersnumbers.app.LettersToNumbersState;
import es.ulpgc.eite.cleancode.lettersnumbers.app.NumbersToLettersState;
import es.ulpgc.eite.cleancode.lettersnumbers.data.LetterData;

public class LetterListPresenter implements LetterListContract.Presenter {

  public static String TAG = "LettersAndNumbers.LetterListPresenter";

  private WeakReference<LetterListContract.View> view;
  private LetterListState state;
  private LetterListContract.Model model;
  private AppMediator mediator;

  public LetterListPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getLetterListState();
  }


  @Override
  public void onStart() {
    Log.e(TAG, "onStart()");
    // TODO: add code if is necessary

  }

  @Override
  public void onRestart() {
    Log.e(TAG, "onRestart()");
    // TODO: add code if is necessary
    view.get().onDataUpdated(state);
  }

  @Override
  public void onResume() {
    Log.e(TAG, "onResume()");
    // TODO: add code if is necessary
    NumbersToLettersState savedState = mediator.getNextLetterListScreenState();
    if(savedState!=null) {
      model.onDataFromNextScreen(savedState.data, savedState.number);
    }

  }

  @Override
  public void onBackPressed() {
    Log.e(TAG, "onBackPressed()");

    // TODO: add code if is necessary
  }

  @Override
  public void onPause() {
    Log.e(TAG, "onPause()");

    // TODO: add code if is necessary
  }

  @Override
  public void onDestroy() {
    Log.e(TAG, "onDestroy()");

    // TODO: add code if is necessary
  }

  @Override
  public void onClickLetterListButton() {
    Log.e(TAG, "onClickLetterListButton()");
    // TODO: add code if is necessary
    model.onAddLetter();
    state.datasource= model.getStoredDatasource();
    view.get().onDataUpdated(state);
  }

  @Override
  public void onClickLetterListCell(LetterData data) {
    Log.e(TAG, "onClickLetterListCell()");
    // TODO: add code if is necessary
    state.number= model.getStoredNumber();
   LettersToNumbersState newState = new LettersToNumbersState(data,state.number);
    Log.e(TAG, "number= "+state.number);
    mediator.setNextLetterListScreenState(newState);
    view.get().navigateToNextScreen();
  }


  @Override
  public void injectView(WeakReference<LetterListContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(LetterListContract.Model model) {
    this.model = model;
  }

}
