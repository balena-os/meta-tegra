DESCRIPTION = "Minimal initramfs image for Tegra186 platforms"
LICENSE = "MIT"

TEGRA_INITRD_INSTALL ??= ""
INITRD_FSTYPES ??= "${INITRAMFS_FSTYPES}"

PACKAGE_INSTALL = "\
    tegra-firmware-xusb \
    tegra210-minimal-init \
    ${TEGRA_INITRD_INSTALL} \
"

IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""

COPY_LIC_MANIFEST = "0"
COPY_LIC_DIRS = "0"

COMPATIBLE_MACHINE = "(tegra186|tegra194)"

KERNELDEPMODDEPEND = ""

IMAGE_ROOTFS_SIZE = "8192"

inherit core-image

IMAGE_FSTYPES = "${INITRD_FSTYPES}"
