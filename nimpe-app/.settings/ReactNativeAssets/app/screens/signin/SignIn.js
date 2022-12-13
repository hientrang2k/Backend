/**
 * Foodvila - React Native Template
 *
 * @format
 * @flow
 */
 import {
  AsyncStorage, 
} from 'react-native';
import axios from "axios";
// import dependencies
import React, { Component } from 'react';
import {
  SafeAreaView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view';
import Constant from '../../appConfig';
// import components
import Button from '../../components/buttons/Button';
import InputModal from '../../components/modals/InputModal';
import UnderlinePasswordInput from '../../components/textinputs/UnderlinePasswordInput';
import UnderlineTextInput from '../../components/textinputs/UnderlineTextInput';

// import colors, layout
import Colors from '../../theme/colors';
import Layout from '../../theme/layout';

// SignIn Config
const PLACEHOLDER_TEXT_COLOR = Colors.onPrimaryColor;
const INPUT_TEXT_COLOR = Colors.onPrimaryColor;
const INPUT_BORDER_COLOR = Colors.onPrimaryColor;
const INPUT_FOCUSED_BORDER_COLOR = Colors.onPrimaryColor;

// SignIn Styles
const styles = StyleSheet.create({
  screenContainer: {
    flex: 1,
    backgroundColor: Colors.primaryColor,
  },
  contentContainerStyle: { flex: 1 },
  content: {
    flex: 1,
    justifyContent: 'space-between',
  },
  form: {
    paddingHorizontal: Layout.LARGE_PADDING,
  },
  inputContainer: { marginBottom: 7 },
  buttonContainer: { paddingTop: 23 },
  forgotPassword: { paddingVertical: 23 },
  forgotPasswordText: {
    fontWeight: '300',
    fontSize: 13,
    color: Colors.onPrimaryColor,
    textAlign: 'center',
  },
  separator: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  line: {
    width: 64,
    height: 1,
    backgroundColor: INPUT_BORDER_COLOR,
  },
  orText: {
    top: -2,
    paddingHorizontal: 8,
    color: PLACEHOLDER_TEXT_COLOR,
  },
  buttonsGroup: {
    paddingTop: 23,
  },
  vSpacer: {
    height: 15,
  },
  footer: {
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
    width: '100%',
  },
  termsContainer: {
    flexDirection: 'row',
  },
  footerText: {
    fontWeight: '300',
    fontSize: 13,
    color: Colors.onPrimaryColor,
  },
  footerLink: {
    fontWeight: '400',
    textDecorationLine: 'underline',
  },
});

const config = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
    Authorization: 'Basic Y29yZV9jbGllbnQ6c2VjcmV0',
  },
};

// SignIn
export default class SignIn extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: 'bvranghammat',
      emailFocused: false,
      password: 'bvrhm@123',
      passwordFocused: false,
      secureTextEntry: true,
      inputModalVisible: false,
    };
  }


  setSession(token) {
    if (token) {
      try {
        // AsyncStorage.setItem('jwt_token', token);
      } catch (error) {
        // Error saving data
      }
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    } else {
      // AsyncStorage.removeItem('jwt_token');
      delete axios.defaults.headers.common['Authorization'];
    }
  }


  emailChange = (text) => {
    this.setState({
      email: text,
    });
  };

  emailFocus = () => {
    this.setState({
      emailFocused: true,
      passwordFocused: false,
    });
  };

  passwordChange = (text) => {
    this.setState({
      password: text,
    });
  };

  passwordFocus = () => {
    this.setState({
      passwordFocused: true,
      emailFocused: false,
    });
  };

  onTogglePress = () => {
    const { secureTextEntry } = this.state;
    this.setState({
      secureTextEntry: !secureTextEntry,
    });
  };

  focusOn = (nextFiled) => () => {
    if (nextFiled) {
      nextFiled.focus();
    }
  };

  showInputModal = (value) => () => {
    this.setState({
      inputModalVisible: value,
    });
  };

  navigateTo = (screen) =>  {
    // const { navigation } = this.props; 
    // navigation.navigate(screen);
  };

  signIn = () => { 
    const { navigation } = this.props;
    this.setState({ loading: true });
    let requestBody = 'client_id=core_client&grant_type=password&client_secret=secret';
    requestBody =
      requestBody + '&username=' + this.state.email + '&password=' + this.state.password;
    axios
      .post(Constant.API_ENPOINT + '/oauth/token', requestBody, config)
      .then((response) => {
        this.setSession(response.data.access_token); 
        // this.navigateTo("HomeNavigator")
        navigation.navigate('HomeNavigator');
      })
      .catch((error) => {
        this.setState({ error: 'Sai tài khoản hoặc mật khẩu' }); 
        alert('Sai tài khoản hoặc mật khẩu');
      });
  };

  componentDidMount = () => {
    let enpoint = Constant.API_ENPOINT;
    if (enpoint) {
      switch (enpoint) {
        case 'http://localhost:8093/asset':
          //local
          this.setState({
            urlLogoLogin: '/assets/images/illustrations/logo_login.jpg',
          });
          break;
        case "http://cloud.ammis.vn:8061/asset":
          //cloud
          this.setState({
            urlLogoLogin: '/assets/images/illustrations/logo_login.jpg',
          });
          break;
        case "http://cloud.assetvn.net:8065/asset":
          // Bệnh viện răng hàm mặt
          this.setState({
            urlLogoLogin:
              '/assets/images/illustrations/logo_asset_management.PNG',
          });
          break;
        case "http://dev.ammis.vn:8094/asset":
          // dev
          this.setState({
            urlLogoLogin: '/assets/images/illustrations/logo_login.jpg',
          });
          break;
        default:
      }
    }
  }

  render() {
    const {
      email,
      emailFocused,
      password,
      passwordFocused,
      secureTextEntry,
      inputModalVisible,
    } = this.state;

    return (
      <SafeAreaView style={styles.screenContainer}>
        <StatusBar
          backgroundColor={Colors.statusBarColor}
          barStyle="dark-content"
        />

        <KeyboardAwareScrollView
          contentContainerStyle={styles.contentContainerStyle}>
          <View style={styles.content}>
            <View />

            <View style={styles.form}>
              <UnderlineTextInput
                onRef={(r) => {
                  this.email = r;
                }}
                onChangeText={this.emailChange}
                onFocus={this.emailFocus}
                inputFocused={emailFocused}
                onSubmitEditing={this.focusOn(this.password)}
                returnKeyType="next"
                blurOnSubmit={false}
                value={email}
                keyboardType="email-address"
                placeholder="Tài khoản"
                placeholderTextColor={PLACEHOLDER_TEXT_COLOR}
                inputTextColor={INPUT_TEXT_COLOR}
                borderColor={INPUT_BORDER_COLOR}
                focusedBorderColor={INPUT_FOCUSED_BORDER_COLOR}
                inputContainerStyle={styles.inputContainer}
              />

              <UnderlinePasswordInput
                onRef={(r) => {
                  this.password = r;
                }}
                onChangeText={this.passwordChange}
                onFocus={this.passwordFocus}
                inputFocused={passwordFocused}
                onSubmitEditing={this.signIn}
                returnKeyType="done"
                value={password}
                placeholder="Mật khẩu"
                placeholderTextColor={PLACEHOLDER_TEXT_COLOR}
                inputTextColor={INPUT_TEXT_COLOR}
                secureTextEntry={secureTextEntry}
                borderColor={INPUT_BORDER_COLOR}
                focusedBorderColor={INPUT_FOCUSED_BORDER_COLOR}
                toggleVisible={password.length > 0}
                toggleText={secureTextEntry ? 'Show' : 'Hide'}
                onTogglePress={this.onTogglePress}
              />

              <View style={styles.buttonContainer}>
                <Button
                  color={'#fff'}
                  rounded
                  borderRadius
                  onPress={this.signIn}
                  title={'Sign in'.toUpperCase()}
                  titleColor={Colors.primaryColor}
                />
              </View>

              <View style={styles.forgotPassword}>
                <Text
                  // onPress={this.showInputModal(true)}
                  onPress={this.navigateTo('ForgotPassword')}
                  style={styles.forgotPasswordText}>
                  Forgot password?
                </Text>
              </View>

              <View style={styles.separator}>
                <View style={styles.line} />
                <Text style={styles.orText}>or</Text>
                <View style={styles.line} />
              </View>

              <View style={styles.buttonsGroup}>
                <Button
                  onPress={this.navigateTo('HomeNavigator')}
                  color="#063d8a"
                  socialIconName="facebook-square"
                  iconColor={Colors.white}
                  title={'Sign in with Facebook'.toUpperCase()}
                  rounded
                  borderRadius
                  titleColor={'#fff'}
                />
                <View style={styles.vSpacer} />
                <Button
                  onPress={this.navigateTo('HomeNavigator')}
                  color="#fe4c1c"
                  socialIconName="google"
                  iconColor={Colors.white}
                  title={'Sign in with Google'.toUpperCase()}
                  rounded
                  borderRadius
                  titleColor={'#fff'}
                />
              </View>
            </View>

            <TouchableWithoutFeedback
              onPress={this.navigateTo('TermsConditions')}>
              <View style={styles.footer}>
                <Text style={styles.footerText}>
                  By signing in, you accepts our
                </Text>
                <View style={styles.termsContainer}>
                  <Text style={[styles.footerText, styles.footerLink]}>
                    Terms & Conditions
                  </Text>
                  <Text style={styles.footerText}> and </Text>
                  <Text style={[styles.footerText, styles.footerLink]}>
                    Privacy Policy
                  </Text>
                  <Text style={styles.footerText}>.</Text>
                </View>
              </View>
            </TouchableWithoutFeedback>
          </View>
        </KeyboardAwareScrollView>

        <InputModal
          title="Forgot password?"
          message="Enter your e-mail address to reset password"
          inputDefaultValue={email}
          inputPlaceholder="E-mail address"
          inputKeyboardType="email-address"
          onRequestClose={this.showInputModal(false)}
          buttonTitle={'Reset password'.toUpperCase()}
          onClosePress={this.showInputModal(false)}
          visible={inputModalVisible}
        />
      </SafeAreaView>
    );
  }
}
