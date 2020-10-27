# dp3t-config-backend-ch

This backend is used by the apps to get their config every 6h. It is used by the Swiss Government to update the 
epidemiological parameters if needed.

The following endpoints are available:

- `/v1` - returns `Hello from DP3T Config WS`
- `/v1/config` - returns a `ConfigResponse` with the following structure and default values:
  - `InfoBoxCollection` - empty by default
    - `deInfoBox` `InfoBox`
      - `title` `String`
      - `msg` `String`
      - `url` `String`
      - `urlTitle` `String`
      - `isDismissible = false`
    - `frInfoBox` `InfoBox`
    - `itInfoBox` `InfoBox`
    - `enInfoBox` `InfoBox`
    - `ptInfoBox` `InfoBox`
    - `esInfoBox` `InfoBox`
    - `sqInfoBox` `InfoBox`
    - `bsInfoBox` `InfoBox`
    - `hrInfoBox` `InfoBox`
    - `srInfoBox` `InfoBox`
    - `rmInfoBox` `InfoBox`
  - `SDKConfig` - will soon be removed (30/7/2020)
    - `numberOfWindowsForExposure = 3`
    - @Deprecated `eventThreshold = 0.8f`
    - @Deprecated `badAttenuationThreshold = 73.0f`
    - `contactAttenuationThreshold = 73.0f`
  - iOSGaenSdkConfig `GAENSDKConfig`
    - `lowerThreshold = 53`
    - `higherThreshold = 60`
    - `factorLow = 1.0d`
    - `factorHigh = 0.5d`
    - `triggerThreshold = 15`
  - androidGaenSdkConfig `GAENSDKConfig`
- `/v1/testinfobox/config` - returns a `ConfigResponse` with only the `SDKConfig` set to a static message

## /config special actions

The call to `/v1/config` does the following special actions:
1. `If iOS == 13.6`: warn that the German 'no risk' message is misleading
1. `If App == Testflight`: warn that the app should be updated to the official app-store app
1. `If App == Initial iOS test app`: set `factorHigh` = `0` to avoid errors
