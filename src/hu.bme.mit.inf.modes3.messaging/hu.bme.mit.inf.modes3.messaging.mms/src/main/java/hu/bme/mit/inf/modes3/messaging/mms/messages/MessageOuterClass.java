// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Message.proto

package hu.bme.mit.inf.modes3.messaging.mms.messages;

public final class MessageOuterClass {
  private MessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rMessage.proto\032\024SegmentCommand.proto\032\022S" +
      "egmentState.proto\032\031TrainCurrentSegment.p" +
      "roto\032\027TrainCurrentSpeed.proto\032\032TrainFunc" +
      "tionCommand.proto\032\031TrainReferenceSpeed.p" +
      "roto\032 TrainReferenceSpeedCommand.proto\032\024" +
      "TurnoutCommand.proto\032\022TurnoutState.proto" +
      "\"\351\005\n\007Message\022\"\n\004type\030\001 \001(\0162\024.Message.Mes" +
      "sageType\022\'\n\016segmentCommand\030\002 \001(\0132\017.Segme" +
      "ntCommand\022#\n\014segmentState\030\003 \001(\0132\r.Segmen" +
      "tState\0221\n\023trainCurrentSegment\030\004 \001(\0132\024.Tr",
      "ainCurrentSegment\022-\n\021trainCurrentSpeed\030\005" +
      " \001(\0132\022.TrainCurrentSpeed\0223\n\024trainFunctio" +
      "nCommand\030\006 \001(\0132\025.TrainFunctionCommand\0221\n" +
      "\023trainReferenceSpeed\030\007 \001(\0132\024.TrainRefere" +
      "nceSpeed\022?\n\032trainReferenceSpeedCommand\030\010" +
      " \001(\0132\033.TrainReferenceSpeedCommand\022\'\n\016tur" +
      "noutCommand\030\t \001(\0132\017.TurnoutCommand\022#\n\014tu" +
      "rnoutState\030\n \001(\0132\r.TurnoutState\"\222\002\n\013Mess" +
      "ageType\022\010\n\004NULL\020\000\022\023\n\017SEGMENT_COMMAND\020\001\022\021" +
      "\n\rSEGMENT_STATE\020\002\022\031\n\025TRAIN_CURRENT_SEGME",
      "NT\020\003\022\027\n\023TRAIN_CURRENT_SPEED\020\004\022\033\n\027TRAIN_D" +
      "IRECTION_COMMAND\020\005\022\032\n\026TRAIN_FUNCTION_COM" +
      "MAND\020\006\022\031\n\025TRAIN_REFERENCE_SPEED\020\007\022!\n\035TRA" +
      "IN_REFERENCE_SPEED_COMMAND\020\010\022\023\n\017TURNOUT_" +
      "COMMAND\020\t\022\021\n\rTURNOUT_STATE\020\nB0\n,hu.bme.m" +
      "it.inf.modes3.messaging.mms.messagesP\001b\006" +
      "proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentCommandOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentStateOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSegmentOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSpeedOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TrainFunctionCommandOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedCommandOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutCommandOuterClass.getDescriptor(),
          hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutStateOuterClass.getDescriptor(),
        }, assigner);
    internal_static_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Message_descriptor,
        new java.lang.String[] { "Type", "SegmentCommand", "SegmentState", "TrainCurrentSegment", "TrainCurrentSpeed", "TrainFunctionCommand", "TrainReferenceSpeed", "TrainReferenceSpeedCommand", "TurnoutCommand", "TurnoutState", });
    hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentCommandOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.SegmentStateOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSegmentOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TrainCurrentSpeedOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TrainFunctionCommandOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TrainReferenceSpeedCommandOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutCommandOuterClass.getDescriptor();
    hu.bme.mit.inf.modes3.messaging.mms.messages.TurnoutStateOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
