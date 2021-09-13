import { requireNativeComponent, ViewStyle } from 'react-native';

type VideoHeaderProps = {
  color: string;
  style: ViewStyle;
};

export const VideoHeaderViewManager = requireNativeComponent<VideoHeaderProps>(
'VideoHeaderView'
);

export default VideoHeaderViewManager;
