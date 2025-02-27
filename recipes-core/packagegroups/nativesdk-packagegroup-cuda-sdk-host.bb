SUMMARY = "Host packages for CUDA SDK toolchain"

inherit packagegroup
inherit nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} = "\
    nativesdk-cuda-compiler \
"
