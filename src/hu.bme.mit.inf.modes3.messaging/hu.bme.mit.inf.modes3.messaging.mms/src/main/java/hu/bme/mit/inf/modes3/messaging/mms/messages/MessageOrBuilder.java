// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Message.proto

package hu.bme.mit.inf.modes3.messaging.mms.messages;

public interface MessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Message)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .Message.MessageType type = 1;</code>
   */
  int getTypeValue();
  /**
   * <code>optional .Message.MessageType type = 1;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.Message.MessageType getType();

  /**
   * <code>optional .SegmentControl segmentControl = 2;</code>
   */
  boolean hasSegmentControl();
  /**
   * <code>optional .SegmentControl segmentControl = 2;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentControl getSegmentControl();
  /**
   * <code>optional .SegmentControl segmentControl = 2;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentControlOrBuilder getSegmentControlOrBuilder();

  /**
   * <code>optional .SegmentState segmentState = 3;</code>
   */
  boolean hasSegmentState();
  /**
   * <code>optional .SegmentState segmentState = 3;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentState getSegmentState();
  /**
   * <code>optional .SegmentState segmentState = 3;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentStateOrBuilder getSegmentStateOrBuilder();

  /**
   * <code>optional .TrainCurrentSegment trainCurrentSegment = 4;</code>
   */
  boolean hasTrainCurrentSegment();
  /**
   * <code>optional .TrainCurrentSegment trainCurrentSegment = 4;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSegment getTrainCurrentSegment();
  /**
   * <code>optional .TrainCurrentSegment trainCurrentSegment = 4;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSegmentOrBuilder getTrainCurrentSegmentOrBuilder();

  /**
   * <code>optional .TrainCurrentSpeed trainCurrentSpeed = 5;</code>
   */
  boolean hasTrainCurrentSpeed();
  /**
   * <code>optional .TrainCurrentSpeed trainCurrentSpeed = 5;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSpeed getTrainCurrentSpeed();
  /**
   * <code>optional .TrainCurrentSpeed trainCurrentSpeed = 5;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSpeedOrBuilder getTrainCurrentSpeedOrBuilder();

  /**
   * <code>optional .TrainDirectionControl trainDirectionControl = 6;</code>
   */
  boolean hasTrainDirectionControl();
  /**
   * <code>optional .TrainDirectionControl trainDirectionControl = 6;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainDirectionControl getTrainDirectionControl();
  /**
   * <code>optional .TrainDirectionControl trainDirectionControl = 6;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainDirectionControlOrBuilder getTrainDirectionControlOrBuilder();

  /**
   * <code>optional .TrainFunctionControl trainFunctionControl = 7;</code>
   */
  boolean hasTrainFunctionControl();
  /**
   * <code>optional .TrainFunctionControl trainFunctionControl = 7;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainFunctionControl getTrainFunctionControl();
  /**
   * <code>optional .TrainFunctionControl trainFunctionControl = 7;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainFunctionControlOrBuilder getTrainFunctionControlOrBuilder();

  /**
   * <code>optional .TrainReferenceSpeed trainReferenceSpeed = 8;</code>
   */
  boolean hasTrainReferenceSpeed();
  /**
   * <code>optional .TrainReferenceSpeed trainReferenceSpeed = 8;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeed getTrainReferenceSpeed();
  /**
   * <code>optional .TrainReferenceSpeed trainReferenceSpeed = 8;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedOrBuilder getTrainReferenceSpeedOrBuilder();

  /**
   * <code>optional .TrainReferenceSpeedControl trainReferenceSpeedControl = 9;</code>
   */
  boolean hasTrainReferenceSpeedControl();
  /**
   * <code>optional .TrainReferenceSpeedControl trainReferenceSpeedControl = 9;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedControl getTrainReferenceSpeedControl();
  /**
   * <code>optional .TrainReferenceSpeedControl trainReferenceSpeedControl = 9;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedControlOrBuilder getTrainReferenceSpeedControlOrBuilder();

  /**
   * <code>optional .TurnoutControl turnoutControl = 10;</code>
   */
  boolean hasTurnoutControl();
  /**
   * <code>optional .TurnoutControl turnoutControl = 10;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutControl getTurnoutControl();
  /**
   * <code>optional .TurnoutControl turnoutControl = 10;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutControlOrBuilder getTurnoutControlOrBuilder();

  /**
   * <code>optional .TurnoutState turnoutState = 11;</code>
   */
  boolean hasTurnoutState();
  /**
   * <code>optional .TurnoutState turnoutState = 11;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutState getTurnoutState();
  /**
   * <code>optional .TurnoutState turnoutState = 11;</code>
   */
  hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutStateOrBuilder getTurnoutStateOrBuilder();
}