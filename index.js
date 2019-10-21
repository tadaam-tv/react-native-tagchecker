import { NativeModules, Platform } from 'react-native';

const { RNTagCheckerModule } = NativeModules;

const updateView = (viewTag, className, props) => {
  // on Android, use custom updateView that catches exception in case the viewTag does not exist
  if (Platform.OS === 'android') {
    RNTagCheckerModule.updateView(viewTag, className, props);
  } else {
    NativeModules.UIManager.updateView(viewTag, className, props);
  }
};

const setHasTVPreferredFocus = viewTag => {
  updateView(viewTag, 'RCTView', {
    hasTVPreferredFocus: true,
  });
};

export { updateView, setHasTVPreferredFocus };
