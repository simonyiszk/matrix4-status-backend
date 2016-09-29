// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: YakinduCanGoTo.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "YakinduCanGoTo.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/common.h>
#include <google/protobuf/stubs/port.h>
#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace modes3
{
namespace protobuf
{

    namespace
    {

        const ::google::protobuf::Descriptor* YakinduCanGoTo_descriptor_ = NULL;
        const ::google::protobuf::internal::GeneratedMessageReflection* YakinduCanGoTo_reflection_ = NULL;

    } // namespace

    void protobuf_AssignDesc_YakinduCanGoTo_2eproto()
    {
        protobuf_AddDesc_YakinduCanGoTo_2eproto();
        const ::google::protobuf::FileDescriptor* file =
            ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName("YakinduCanGoTo.proto");
        GOOGLE_CHECK(file != NULL);
        YakinduCanGoTo_descriptor_ = file->message_type(0);
        static const int YakinduCanGoTo_offsets_[2] = {
            GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(YakinduCanGoTo, targetid_),
            GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(YakinduCanGoTo, direction_),
        };
        YakinduCanGoTo_reflection_ =
            ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
                YakinduCanGoTo_descriptor_, YakinduCanGoTo::default_instance_, YakinduCanGoTo_offsets_, -1, -1, -1,
                sizeof(YakinduCanGoTo),
                GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(YakinduCanGoTo, _internal_metadata_),
                GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(YakinduCanGoTo, _is_default_instance_));
    }

    namespace
    {

        GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
        inline void protobuf_AssignDescriptorsOnce()
        {
            ::google::protobuf::GoogleOnceInit(
                &protobuf_AssignDescriptors_once_, &protobuf_AssignDesc_YakinduCanGoTo_2eproto);
        }

        void protobuf_RegisterTypes(const ::std::string&)
        {
            protobuf_AssignDescriptorsOnce();
            ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
                YakinduCanGoTo_descriptor_, &YakinduCanGoTo::default_instance());
        }

    } // namespace

    void protobuf_ShutdownFile_YakinduCanGoTo_2eproto()
    {
        delete YakinduCanGoTo::default_instance_;
        delete YakinduCanGoTo_reflection_;
    }

    void protobuf_AddDesc_YakinduCanGoTo_2eproto()
    {
        static bool already_here = false;
        if(already_here)
            return;
        already_here = true;
        GOOGLE_PROTOBUF_VERIFY_VERSION;

        ::modes3::protobuf::protobuf_AddDesc_Enums_2eproto();
        ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
            "\n\024YakinduCanGoTo.proto\022\017modes3.protobuf\032"
            "\013Enums.proto\"b\n\016YakinduCanGoTo\022\020\n\010target"
            "ID\030\001 \001(\r\022>\n\tdirection\030\002 \001(\0162+.modes3.pro"
            "tobuf.YakinduConnectionDirectionB0\n,hu.b"
            "me.mit.inf.modes3.messaging.mms.messages"
            "P\001b\006proto3",
            210);
        ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
            "YakinduCanGoTo.proto", &protobuf_RegisterTypes);
        YakinduCanGoTo::default_instance_ = new YakinduCanGoTo();
        YakinduCanGoTo::default_instance_->InitAsDefaultInstance();
        ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_YakinduCanGoTo_2eproto);
    }

    // Force AddDescriptors() to be called at static initialization time.
    struct StaticDescriptorInitializer_YakinduCanGoTo_2eproto {
        StaticDescriptorInitializer_YakinduCanGoTo_2eproto()
        {
            protobuf_AddDesc_YakinduCanGoTo_2eproto();
        }
    } static_descriptor_initializer_YakinduCanGoTo_2eproto_;

    namespace
    {

        static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD;
        static void MergeFromFail(int line)
        {
            GOOGLE_CHECK(false) << __FILE__ << ":" << line;
        }

    } // namespace

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
    const int YakinduCanGoTo::kTargetIDFieldNumber;
    const int YakinduCanGoTo::kDirectionFieldNumber;
#endif // !defined(_MSC_VER) || _MSC_VER >= 1900

    YakinduCanGoTo::YakinduCanGoTo()
        : ::google::protobuf::Message()
        , _internal_metadata_(NULL)
    {
        SharedCtor();
        // @@protoc_insertion_point(constructor:modes3.protobuf.YakinduCanGoTo)
    }

    void YakinduCanGoTo::InitAsDefaultInstance()
    {
        _is_default_instance_ = true;
    }

    YakinduCanGoTo::YakinduCanGoTo(const YakinduCanGoTo& from)
        : ::google::protobuf::Message()
        , _internal_metadata_(NULL)
    {
        SharedCtor();
        MergeFrom(from);
        // @@protoc_insertion_point(copy_constructor:modes3.protobuf.YakinduCanGoTo)
    }

    void YakinduCanGoTo::SharedCtor()
    {
        _is_default_instance_ = false;
        _cached_size_ = 0;
        targetid_ = 0u;
        direction_ = 0;
    }

    YakinduCanGoTo::~YakinduCanGoTo()
    {
        // @@protoc_insertion_point(destructor:modes3.protobuf.YakinduCanGoTo)
        SharedDtor();
    }

    void YakinduCanGoTo::SharedDtor()
    {
        if(this != default_instance_) {
        }
    }

    void YakinduCanGoTo::SetCachedSize(int size) const
    {
        GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
        _cached_size_ = size;
        GOOGLE_SAFE_CONCURRENT_WRITES_END();
    }
    const ::google::protobuf::Descriptor* YakinduCanGoTo::descriptor()
    {
        protobuf_AssignDescriptorsOnce();
        return YakinduCanGoTo_descriptor_;
    }

    const YakinduCanGoTo& YakinduCanGoTo::default_instance()
    {
        if(default_instance_ == NULL)
            protobuf_AddDesc_YakinduCanGoTo_2eproto();
        return *default_instance_;
    }

    YakinduCanGoTo* YakinduCanGoTo::default_instance_ = NULL;

    YakinduCanGoTo* YakinduCanGoTo::New(::google::protobuf::Arena* arena) const
    {
        YakinduCanGoTo* n = new YakinduCanGoTo;
        if(arena != NULL) {
            arena->Own(n);
        }
        return n;
    }

    void YakinduCanGoTo::Clear()
    {
// @@protoc_insertion_point(message_clear_start:modes3.protobuf.YakinduCanGoTo)
#if defined(__clang__)
#define ZR_HELPER_(f)                                                                                               \
    _Pragma("clang diagnostic push") _Pragma("clang diagnostic ignored \"-Winvalid-offsetof\"") __builtin_offsetof( \
        YakinduCanGoTo, f) _Pragma("clang diagnostic pop")
#else
#define ZR_HELPER_(f) reinterpret_cast<char*>(&reinterpret_cast<YakinduCanGoTo*>(16)->f)
#endif

#define ZR_(first, last)                                                          \
    do {                                                                          \
        ::memset(&first, 0, ZR_HELPER_(last) - ZR_HELPER_(first) + sizeof(last)); \
    } while(0)

        ZR_(targetid_, direction_);

#undef ZR_HELPER_
#undef ZR_
    }

    bool YakinduCanGoTo::MergePartialFromCodedStream(::google::protobuf::io::CodedInputStream* input)
    {
#define DO_(EXPRESSION)                  \
    if(!GOOGLE_PREDICT_TRUE(EXPRESSION)) \
    goto failure
        ::google::protobuf::uint32 tag;
        // @@protoc_insertion_point(parse_start:modes3.protobuf.YakinduCanGoTo)
        for(;;) {
            ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
            tag = p.first;
            if(!p.second)
                goto handle_unusual;
            switch(::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
            // optional uint32 targetID = 1;
            case 1: {
                if(tag == 8) {
                    DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive< ::google::protobuf::uint32,
                        ::google::protobuf::internal::WireFormatLite::TYPE_UINT32>(input, &targetid_)));

                } else {
                    goto handle_unusual;
                }
                if(input->ExpectTag(16))
                    goto parse_direction;
                break;
            }

            // optional .modes3.protobuf.YakinduConnectionDirection direction = 2;
            case 2: {
                if(tag == 16) {
                parse_direction:
                    int value;
                    DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<int,
                        ::google::protobuf::internal::WireFormatLite::TYPE_ENUM>(input, &value)));
                    set_direction(static_cast< ::modes3::protobuf::YakinduConnectionDirection>(value));
                } else {
                    goto handle_unusual;
                }
                if(input->ExpectAtEnd())
                    goto success;
                break;
            }

            default: {
            handle_unusual:
                if(tag == 0 ||
                    ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
                        ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
                    goto success;
                }
                DO_(::google::protobuf::internal::WireFormatLite::SkipField(input, tag));
                break;
            }
            }
        }
    success:
        // @@protoc_insertion_point(parse_success:modes3.protobuf.YakinduCanGoTo)
        return true;
    failure:
        // @@protoc_insertion_point(parse_failure:modes3.protobuf.YakinduCanGoTo)
        return false;
#undef DO_
    }

    void YakinduCanGoTo::SerializeWithCachedSizes(::google::protobuf::io::CodedOutputStream* output) const
    {
        // @@protoc_insertion_point(serialize_start:modes3.protobuf.YakinduCanGoTo)
        // optional uint32 targetID = 1;
        if(this->targetid() != 0) {
            ::google::protobuf::internal::WireFormatLite::WriteUInt32(1, this->targetid(), output);
        }

        // optional .modes3.protobuf.YakinduConnectionDirection direction = 2;
        if(this->direction() != 0) {
            ::google::protobuf::internal::WireFormatLite::WriteEnum(2, this->direction(), output);
        }

        // @@protoc_insertion_point(serialize_end:modes3.protobuf.YakinduCanGoTo)
    }

    ::google::protobuf::uint8* YakinduCanGoTo::InternalSerializeWithCachedSizesToArray(bool deterministic,
        ::google::protobuf::uint8* target) const
    {
        // @@protoc_insertion_point(serialize_to_array_start:modes3.protobuf.YakinduCanGoTo)
        // optional uint32 targetID = 1;
        if(this->targetid() != 0) {
            target = ::google::protobuf::internal::WireFormatLite::WriteUInt32ToArray(1, this->targetid(), target);
        }

        // optional .modes3.protobuf.YakinduConnectionDirection direction = 2;
        if(this->direction() != 0) {
            target = ::google::protobuf::internal::WireFormatLite::WriteEnumToArray(2, this->direction(), target);
        }

        // @@protoc_insertion_point(serialize_to_array_end:modes3.protobuf.YakinduCanGoTo)
        return target;
    }

    int YakinduCanGoTo::ByteSize() const
    {
        // @@protoc_insertion_point(message_byte_size_start:modes3.protobuf.YakinduCanGoTo)
        int total_size = 0;

        // optional uint32 targetID = 1;
        if(this->targetid() != 0) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::UInt32Size(this->targetid());
        }

        // optional .modes3.protobuf.YakinduConnectionDirection direction = 2;
        if(this->direction() != 0) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::EnumSize(this->direction());
        }

        GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
        _cached_size_ = total_size;
        GOOGLE_SAFE_CONCURRENT_WRITES_END();
        return total_size;
    }

    void YakinduCanGoTo::MergeFrom(const ::google::protobuf::Message& from)
    {
        // @@protoc_insertion_point(generalized_merge_from_start:modes3.protobuf.YakinduCanGoTo)
        if(GOOGLE_PREDICT_FALSE(&from == this))
            MergeFromFail(__LINE__);
        const YakinduCanGoTo* source =
            ::google::protobuf::internal::DynamicCastToGenerated<const YakinduCanGoTo>(&from);
        if(source == NULL) {
            // @@protoc_insertion_point(generalized_merge_from_cast_fail:modes3.protobuf.YakinduCanGoTo)
            ::google::protobuf::internal::ReflectionOps::Merge(from, this);
        } else {
            // @@protoc_insertion_point(generalized_merge_from_cast_success:modes3.protobuf.YakinduCanGoTo)
            MergeFrom(*source);
        }
    }

    void YakinduCanGoTo::MergeFrom(const YakinduCanGoTo& from)
    {
        // @@protoc_insertion_point(class_specific_merge_from_start:modes3.protobuf.YakinduCanGoTo)
        if(GOOGLE_PREDICT_FALSE(&from == this))
            MergeFromFail(__LINE__);
        if(from.targetid() != 0) {
            set_targetid(from.targetid());
        }
        if(from.direction() != 0) {
            set_direction(from.direction());
        }
    }

    void YakinduCanGoTo::CopyFrom(const ::google::protobuf::Message& from)
    {
        // @@protoc_insertion_point(generalized_copy_from_start:modes3.protobuf.YakinduCanGoTo)
        if(&from == this)
            return;
        Clear();
        MergeFrom(from);
    }

    void YakinduCanGoTo::CopyFrom(const YakinduCanGoTo& from)
    {
        // @@protoc_insertion_point(class_specific_copy_from_start:modes3.protobuf.YakinduCanGoTo)
        if(&from == this)
            return;
        Clear();
        MergeFrom(from);
    }

    bool YakinduCanGoTo::IsInitialized() const
    {

        return true;
    }

    void YakinduCanGoTo::Swap(YakinduCanGoTo* other)
    {
        if(other == this)
            return;
        InternalSwap(other);
    }
    void YakinduCanGoTo::InternalSwap(YakinduCanGoTo* other)
    {
        std::swap(targetid_, other->targetid_);
        std::swap(direction_, other->direction_);
        _internal_metadata_.Swap(&other->_internal_metadata_);
        std::swap(_cached_size_, other->_cached_size_);
    }

    ::google::protobuf::Metadata YakinduCanGoTo::GetMetadata() const
    {
        protobuf_AssignDescriptorsOnce();
        ::google::protobuf::Metadata metadata;
        metadata.descriptor = YakinduCanGoTo_descriptor_;
        metadata.reflection = YakinduCanGoTo_reflection_;
        return metadata;
    }

#if PROTOBUF_INLINE_NOT_IN_HEADERS
    // YakinduCanGoTo

    // optional uint32 targetID = 1;
    void YakinduCanGoTo::clear_targetid()
    {
        targetid_ = 0u;
    }
    ::google::protobuf::uint32 YakinduCanGoTo::targetid() const
    {
        // @@protoc_insertion_point(field_get:modes3.protobuf.YakinduCanGoTo.targetID)
        return targetid_;
    }
    void YakinduCanGoTo::set_targetid(::google::protobuf::uint32 value)
    {

        targetid_ = value;
        // @@protoc_insertion_point(field_set:modes3.protobuf.YakinduCanGoTo.targetID)
    }

    // optional .modes3.protobuf.YakinduConnectionDirection direction = 2;
    void YakinduCanGoTo::clear_direction()
    {
        direction_ = 0;
    }
    ::modes3::protobuf::YakinduConnectionDirection YakinduCanGoTo::direction() const
    {
        // @@protoc_insertion_point(field_get:modes3.protobuf.YakinduCanGoTo.direction)
        return static_cast< ::modes3::protobuf::YakinduConnectionDirection>(direction_);
    }
    void YakinduCanGoTo::set_direction(::modes3::protobuf::YakinduConnectionDirection value)
    {

        direction_ = value;
        // @@protoc_insertion_point(field_set:modes3.protobuf.YakinduCanGoTo.direction)
    }

#endif // PROTOBUF_INLINE_NOT_IN_HEADERS

    // @@protoc_insertion_point(namespace_scope)

} // namespace protobuf
} // namespace modes3

// @@protoc_insertion_point(global_scope)